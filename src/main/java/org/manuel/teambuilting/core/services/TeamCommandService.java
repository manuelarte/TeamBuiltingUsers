/**
 * 
 */
package org.manuel.teambuilting.core.services;

import com.auth0.authentication.result.UserProfile;
import com.auth0.spring.security.api.Auth0JWTToken;
import org.manuel.teambuilting.core.config.Auth0Client;
import org.manuel.teambuilting.core.messages.TeamEventMessage;
import org.manuel.teambuilting.core.model.Team;
import org.manuel.teambuilting.core.model.repository.TeamRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.inject.Inject;
import java.util.Date;

/**
 * @author Manuel Doncel Martos
 *
 */
@Service
public class TeamCommandService {

	@Value("${messaging.event.amqp.exchange}")
	private String teamExchangeName;

	@Value("${messaging.event.amqp.team-event-routing-key}")
	private String teamEventRoutingKey;


	private final TeamRepository teamRepository;
	private final RabbitTemplate rabbitTemplate;

	private final Auth0Client auth0Client;


	@Inject
	public TeamCommandService(final TeamRepository teamRepository, final RabbitTemplate rabbitTemplate, final Auth0Client auth0Client) {
		this.teamRepository = teamRepository;
		this.rabbitTemplate = rabbitTemplate;
		this.auth0Client = auth0Client;
	}

	@PreAuthorize("hasAuthority('user') or hasAuthority('admin')")
	public Team createTeam(final Team team) {
		Assert.notNull(team);
		final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		final UserProfile userProfile = auth0Client.getUser((Auth0JWTToken) auth);
		final Team savedTeam = teamRepository.save(team);
		sendMessage(savedTeam, "CREATED", userProfile);
		return savedTeam;
	}

	private void sendMessage(final Team savedTeam, final String changeType, final UserProfile userProfile) {
		final TeamEventMessage message = new TeamEventMessage(savedTeam, userProfile.getId(), changeType, new Date());
		rabbitTemplate.convertAndSend(teamExchangeName, teamEventRoutingKey, message);
	}



}
