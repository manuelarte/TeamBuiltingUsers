package org.manuel.teambuilting.core.services;

import java.util.Set;

import javax.inject.Inject;

import org.manuel.teambuilting.core.model.Player;
import org.manuel.teambuilting.core.model.PlayerId;
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

	@Value("${messaging.event.amqp.exchange.name}")
	private String exchangeName;

	private final PlayerRepository playerRepository;
	private final RabbitTemplate rabbitTemplate;

	@Inject
	public PlayerQueryService(final PlayerRepository playerRepository, final RabbitTemplate rabbitTemplate) {
		this.playerRepository = playerRepository;
		this.rabbitTemplate = rabbitTemplate;
	}

	public Player getPlayer(final PlayerId playerId) {
		return playerRepository.findOne(playerId.getId());
	}

	public Set<Player> findPlayerByName(final String name) {
		return playerRepository.findByNameLikeIgnoreCase(name);
	}

}
