package org.manuel.teambuilting.core.services;

import java.util.Set;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;

import org.manuel.teambuilting.core.messages.TeamChangedMessage;
import org.manuel.teambuilting.core.model.Team;
import org.manuel.teambuilting.core.model.TeamId;
import org.manuel.teambuilting.core.model.repository.TeamRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * @author manuel.doncel.martos
 * @since 16-12-2016
 */
@Service
public class TeamQueryService {

	@Value("${messaging.event.amqp.exchange}")
	private String teamExchangeName;

	@Value("${messaging.event.amqp.team-crud-routing-key}")
	private String crudRoutingKey;

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

	public Team getTeam(@NotNull final TeamId teamId) {
		Assert.notNull(teamId);
		final Team retrieved = repository.findOne(teamId.getId());
		sendMessage(retrieved, "RETRIEVED");
		return retrieved;
	}

	private void sendMessage(final Team savedTeam, final String changeType) {
		final TeamChangedMessage message = new TeamChangedMessage(savedTeam, changeType);
		rabbitTemplate.convertAndSend(teamExchangeName, crudRoutingKey, message);
	}
}
