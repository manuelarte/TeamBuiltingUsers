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

	@Value("${messaging.event.amqp.exchange.name}")
	private String teamExchangeName;

	@Value("${messaging.event.amqp.team.queue.team-visited-routing-key}")
	private String teamVisitedRoutingKey;

	private final TeamRepository repository;
	private final RabbitTemplate rabbitTemplate;

	@Inject
	public TeamQueryService(final TeamRepository repository, final RabbitTemplate rabbitTemplate) {
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
		sendMessage(retrieved, userProfile);
		return retrieved;
	}

	private void sendMessage(final Team savedTeam, final Optional<UserProfile> userProfile) {
		final String userId = userProfile.isPresent() ? userProfile.get().getId() : null;
		final TeamVisitedMessage message = new TeamVisitedMessage(savedTeam, userId, new Date());
		rabbitTemplate.convertAndSend(teamExchangeName, teamVisitedRoutingKey, message);
	}
}
