/**
 * 
 */
package org.manuel.teambuilting.core.services;

import javax.inject.Inject;

import org.manuel.teambuilting.core.dtos.TeamDTO;
import org.manuel.teambuilting.core.messages.TeamChangedMessage;
import org.manuel.teambuilting.core.model.Team;
import org.manuel.teambuilting.core.model.repository.TeamHistRepository;
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
public class TeamService {

	@Value("${messaging.teamexchange.name}")
	private String teamExchangeName;

	@Value("${messaging.teamexchange.crudroutingkey}")
	private String crudRoutingKey;


	private final TeamRepository teamRepository;
	private final RabbitTemplate rabbitTemplate;

	private final DTOSConverter dtosConverter;


	@Inject
	public TeamService(final TeamRepository teamRepository, final RabbitTemplate rabbitTemplate, final DTOSConverter dtosConverter) {
		this.teamRepository = teamRepository;
		this.dtosConverter = dtosConverter;
		this.rabbitTemplate = rabbitTemplate;
	}

	@PreAuthorize("hasAuthority('ROLE_USER')")
	public TeamDTO createTeam(final TeamDTO team) {
		Assert.notNull(team);
		final Team savedTeam = teamRepository.save(dtosConverter.createTeam(team));
		sendMessage(savedTeam, "CREATED");
		return dtosConverter.createTeamDTO(savedTeam);

	}

	private void sendMessage(final Team savedTeam, final String changeType) {
		final TeamChangedMessage message = new TeamChangedMessage(savedTeam, changeType);
		rabbitTemplate.convertAndSend(teamExchangeName, crudRoutingKey, message);
	}



}
