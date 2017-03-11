package org.manuel.teambuilting.core.services;

import com.auth0.authentication.result.UserProfile;

import java.util.Date;
import java.util.Optional;

import javax.inject.Inject;

import org.manuel.teambuilting.core.messages.PlayerVisitedMessage;
import org.manuel.teambuilting.core.model.Player;
import org.manuel.teambuilting.core.repositories.PlayerRepository;
import org.manuel.teambuilting.core.util.Util;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author manuel.doncel.martos
 * @since 16-12-2016
 */
@Service
public class PlayerQueryService {

	private static final String PLAYER_VISITED_ROUTING_KEY = "player.visited";


	/**
	 * Exchange name for the players
	 */
	private final String playerExchangeName;
	private final PlayerRepository playerRepository;
	private final RabbitTemplate rabbitTemplate;
	private final Util util;

	@Inject
	public PlayerQueryService(final @Value("${messaging.amqp.player.exchange.name}") String playerExchangeName,
		final PlayerRepository playerRepository, final RabbitTemplate rabbitTemplate, final Util util) {
		this.playerExchangeName = playerExchangeName;
		this.playerRepository = playerRepository;
		this.rabbitTemplate = rabbitTemplate;
		this.util = util;
	}

	public Optional<Player> getPlayer(final String playerId) {
		final Optional<Player> retrieved = Optional.ofNullable(playerRepository.findOne(playerId));
		if (retrieved.isPresent()) {
			sendPlayerVisitedMessage(retrieved.get());
		}
		return retrieved;
	}

	public Page<Player> findPlayerByName(final Pageable pageable, final String name) {
		return playerRepository.findByNameLikeIgnoreCase(pageable, name);
	}

	private void sendPlayerVisitedMessage(final Player visitedPlayer) {
		final Optional<UserProfile> userProfile = util.getUserProfile();
		final String userId = userProfile.isPresent() ? userProfile.get().getId() : null;
		final PlayerVisitedMessage message = new PlayerVisitedMessage(visitedPlayer, userId, new Date());
		rabbitTemplate.convertAndSend(playerExchangeName, PLAYER_VISITED_ROUTING_KEY, message);
	}

}
