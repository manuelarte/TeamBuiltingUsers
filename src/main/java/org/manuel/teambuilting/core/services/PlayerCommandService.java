package org.manuel.teambuilting.core.services;

import javax.inject.Inject;

import org.manuel.teambuilting.core.model.Player;
import org.manuel.teambuilting.core.model.PlayerId;
import org.manuel.teambuilting.core.model.repository.PlayerRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class PlayerCommandService {

	private final PlayerRepository playerRepository;

	@Inject
	public PlayerCommandService(final PlayerRepository playerRepository) {
		this.playerRepository = playerRepository;
	}

	@PreAuthorize("hasAuthority('user') or hasAuthority('admin')")
	public Player savePlayer(final Player player) {
		return playerRepository.save(player);
	}

	@PreAuthorize("hasAuthority('user') or hasAuthority('admin')")
	public void deletePlayer(final PlayerId playerId) {
		playerRepository.delete(playerId.getId());
	}

}