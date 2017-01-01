package org.manuel.teambuilting.core.services;

import com.auth0.authentication.result.UserProfile;
import org.manuel.teambuilting.core.messages.TeamEventMessage;
import org.manuel.teambuilting.core.model.Team;
import org.manuel.teambuilting.core.model.TeamId;
import org.manuel.teambuilting.core.repositories.TeamRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author manuel.doncel.martos
 * @since 16-12-2016
 */
@Service
public class TeamQueryService {

	@Value("${messaging.event.amqp.exchange}")
	private String teamExchangeName;

	@Value("${messaging.event.amqp.team-event-routing-key}")
	private String teamEventRoutingKey;

	private final TeamRepository repository;
	private final RabbitTemplate rabbitTemplate;

	@Inject
	public TeamQueryService(final TeamRepository repository, final RabbitTemplate rabbitTemplate) {
		this.repository = repository;
		this.rabbitTemplate = rabbitTemplate;
	}


	@Cacheable
	public Set<Team> findTeamBy(final String sport, final String name) {
		final Set<Team> teamsForSport = repository.findBySportLikeIgnoreCase(sport);
		final Set<Team> matchingName = repository.findByNameLikeIgnoreCase(name);
		return matchingName.stream()
			.filter(team -> teamsForSport.contains(team)).collect(Collectors.toSet());
	}

	public Team getTeam(@NotNull final TeamId teamId, final Optional<UserProfile> userProfile) {
		Assert.notNull(teamId);
        Assert.notNull(userProfile);
		final Team retrieved = repository.findOne(teamId.getId());
		sendMessage(retrieved, "VISITED", userProfile);
		return retrieved;
	}

	private void sendMessage(final Team savedTeam, final String changeType, final Optional<UserProfile> userProfile) {
		final String user_id = userProfile.isPresent() ? userProfile.get().getId() : null;
		final TeamEventMessage message = new TeamEventMessage(savedTeam, user_id, changeType, new Date());
		rabbitTemplate.convertAndSend(teamExchangeName, teamEventRoutingKey, message);
	}
}
