package org.manuel.teambuilting.core.services;

import org.manuel.teambuilting.core.dtos.PlayerDTO;
import org.manuel.teambuilting.core.model.Player;
import org.manuel.teambuilting.core.model.PlayerId;
import org.manuel.teambuilting.core.model.repository.PlayerRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PlayerService {

	private final PlayerRepository playerRepository;
	private final DTOSConverter dtosConverter;

	@Inject
	public PlayerService(final PlayerRepository playerRepository, final DTOSConverter dtosConverter) {
		this.playerRepository = playerRepository;
		this.dtosConverter = dtosConverter;
	}

	public PlayerDTO getPlayer(final PlayerId playerId) {
		final Player player = playerRepository.findOne(playerId.getId());
		return dtosConverter.toPlayerDTO(player);
	}

	@PreAuthorize("hasAuthority('user') or hasAuthority('admin')")
	public PlayerDTO savePlayer(final PlayerDTO player) {
		final Player created = playerRepository.save(dtosConverter.toPlayer(player));
		return dtosConverter.toPlayerDTO(created);
	}

	@PreAuthorize("hasAuthority('user') or hasAuthority('admin')")
	public void deletePlayer(final PlayerId playerId) {
		playerRepository.delete(playerId.getId());
	}


	public Set<PlayerDTO> findPlayerByName(final String name) {
		final Set<Player> players = playerRepository.findByNameLikeIgnoreCase(name);
		return players.stream().map(player -> dtosConverter.toPlayerDTO(player)).collect(Collectors.toSet());
	}

}