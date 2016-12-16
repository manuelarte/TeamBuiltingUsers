package org.manuel.teambuilting.core.services;

import java.util.Set;

import javax.inject.Inject;

import org.manuel.teambuilting.core.model.Player;
import org.manuel.teambuilting.core.model.PlayerId;
import org.manuel.teambuilting.core.model.repository.PlayerRepository;
import org.springframework.stereotype.Service;

/**
 * @author manuel.doncel.martos
 * @since 16-12-2016
 */
@Service
public class PlayerQueryService {

	private final PlayerRepository playerRepository;

	@Inject
	public PlayerQueryService(final PlayerRepository playerRepository) {
		this.playerRepository = playerRepository;
	}

	public Player getPlayer(final PlayerId playerId) {
		return playerRepository.findOne(playerId.getId());
	}

	public Set<Player> findPlayerByName(final String name) {
		return playerRepository.findByNameLikeIgnoreCase(name);
	}

}
