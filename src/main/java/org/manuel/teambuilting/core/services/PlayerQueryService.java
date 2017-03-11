package org.manuel.teambuilting.core.services;

import java.util.Set;

import javax.inject.Inject;

import org.manuel.teambuilting.core.model.Player;
import org.manuel.teambuilting.core.repositories.PlayerRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author manuel.doncel.martos
 * @since 16-12-2016
 */
@Service
public class PlayerQueryService {

	private final String playerExchangeName;

	private final PlayerRepository playerRepository;
	private final RabbitTemplate rabbitTemplate;

	@Inject
	public PlayerQueryService(final @Value("${messaging.amqp.player.exchange.name}") String playerExchangeName,
		final PlayerRepository playerRepository, final RabbitTemplate rabbitTemplate) {
		this.playerExchangeName = playerExchangeName;
		this.playerRepository = playerRepository;
		this.rabbitTemplate = rabbitTemplate;
	}

	public Player getPlayer(final String playerId) {
		// TODO send player visited message event
		return playerRepository.findOne(playerId);
	}

	public Set<Player> findPlayerByName(final String name) {
		return playerRepository.findByNameLikeIgnoreCase(name);
	}

}
