package org.manuel.teambuilting.core.services;

import com.auth0.authentication.result.UserProfile;

import java.util.Date;
import java.util.Optional;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;

import org.manuel.teambuilting.core.messages.TeamVisitedMessage;
import org.manuel.teambuilting.core.model.Team;
import org.manuel.teambuilting.core.repositories.TeamRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * @author manuel.doncel.martos
 * @since 16-12-2016
 */
@Service
public class TeamQueryService {

	private static final String TEAM_VISITED_ROUTING_KEY = "team.visited";

	private String teamExchangeName;
	private final TeamRepository repository;
	private final RabbitTemplate rabbitTemplate;

	@Inject
	public TeamQueryService(final @Value("${messaging.amqp.team.exchange.name}") String teamExchangeName,
		final TeamRepository repository, final RabbitTemplate rabbitTemplate) {
		this.teamExchangeName = teamExchangeName;
		this.repository = repository;
		this.rabbitTemplate = rabbitTemplate;
	}

	@Cacheable
	public Page<Team> findTeamBy(final Pageable pageable, final String sport, final String name) {
		return repository.findBySportLikeIgnoreCaseAndNameLikeIgnoreCase(pageable, sport, name);
	}

	public Team getTeam(@NotNull final String teamId, final Optional<UserProfile> userProfile) {
		Assert.hasLength(teamId);
        final Team retrieved = repository.findOne(teamId);
		sendTeamVisitedMessage(retrieved, userProfile);
		return retrieved;
	}

	private void sendTeamVisitedMessage(final Team savedTeam, final Optional<UserProfile> userProfile) {
		final String userId = userProfile.isPresent() ? userProfile.get().getId() : null;
		final TeamVisitedMessage message = new TeamVisitedMessage(savedTeam, userId, new Date());
		rabbitTemplate.convertAndSend(teamExchangeName, TEAM_VISITED_ROUTING_KEY, message);
	}
}
