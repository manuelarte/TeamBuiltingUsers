package org.manuel.teambuilting.core.services;

import com.auth0.authentication.result.UserProfile;
import com.auth0.spring.security.api.Auth0JWTToken;

import java.util.Date;

import javax.inject.Inject;

import org.manuel.teambuilting.core.aspects.UserDataDeletePlayer;
import org.manuel.teambuilting.core.aspects.UserDataSave;
import org.manuel.teambuilting.core.config.Auth0Client;
import org.manuel.teambuilting.core.messages.PlayerDeletedMessage;
import org.manuel.teambuilting.core.model.Player;
import org.manuel.teambuilting.core.model.PlayerId;
import org.manuel.teambuilting.core.repositories.PlayerRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class PlayerCommandService {

	@Value("${messaging.event.amqp.exchange}")
	private String exchangeName;

	@Value("${messaging.event.amqp.player-deleted-routing-key}")
	private String playerDeletedRoutingKey;


	private final PlayerRepository playerRepository;
	private final Auth0Client auth0Client;
	private final RabbitTemplate rabbitTemplate;

	@Inject
	public PlayerCommandService(final PlayerRepository playerRepository, final Auth0Client auth0Client, final RabbitTemplate rabbitTemplate) {
		this.playerRepository = playerRepository;
		this.rabbitTemplate = rabbitTemplate;
		this.auth0Client = auth0Client;
	}

	@PreAuthorize("hasAuthority('user') or hasAuthority('admin')")
	@UserDataSave
	public Player savePlayer(final Player player) {
		return playerRepository.save(player);
	}

	@PreAuthorize("hasAuthority('user') or hasAuthority('admin')")
	@UserDataDeletePlayer
	public void deletePlayer(final PlayerId playerId) {
		final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		final UserProfile userProfile = auth0Client.getUser((Auth0JWTToken) auth);
		playerRepository.delete(playerId.getId());
		sendPlayerDeletedMessage(playerRepository.findOne(playerId.getId()), userProfile);
	}

	private void sendPlayerDeletedMessage(final Player player, final UserProfile userProfile) {
		final PlayerDeletedMessage message = new PlayerDeletedMessage(player, userProfile.getId(), new Date());
		rabbitTemplate.convertAndSend(exchangeName, playerDeletedRoutingKey, message);
	}

}