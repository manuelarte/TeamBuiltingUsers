/**
 * 
 */
package org.manuel.teambuilting.core.services;

import javax.inject.Inject;

import org.manuel.teambuilting.core.messages.TeamChangedMessage;
import org.manuel.teambuilting.core.model.Team;
import org.manuel.teambuilting.core.model.repository.TeamRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * @author Manuel Doncel Martos
 *
 */
@Service
public class TeamCommandService {

	@Value("${messaging.event.amqp.exchange}")
	private String teamExchangeName;

	@Value("${messaging.event.amqp.team-crud-routing-key}")
	private String crudRoutingKey;


	private final TeamRepository teamRepository;
	private final RabbitTemplate rabbitTemplate;


	@Inject
	public TeamCommandService(final TeamRepository teamRepository, final RabbitTemplate rabbitTemplate) {
		this.teamRepository = teamRepository;
		this.rabbitTemplate = rabbitTemplate;
	}

	@PreAuthorize("hasAuthority('user') or hasAuthority('admin')")
	public Team createTeam(final Team team) {
		Assert.notNull(team);
		final Team savedTeam = teamRepository.save(team);
		sendMessage(savedTeam, "CREATED");
		return savedTeam;
	}

	private void sendMessage(final Team savedTeam, final String changeType) {
		final TeamChangedMessage message = new TeamChangedMessage(savedTeam, changeType);
		rabbitTemplate.convertAndSend(teamExchangeName, crudRoutingKey, message);
	}



}
