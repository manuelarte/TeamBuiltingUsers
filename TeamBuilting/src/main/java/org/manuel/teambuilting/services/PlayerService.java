package org.manuel.teambuilting.services;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.manuel.teambuilting.dtos.PlayerDTO;
import org.manuel.teambuilting.dtos.PlayerToTeamDTO;
import org.manuel.teambuilting.model.Player;
import org.manuel.teambuilting.model.PlayerId;
import org.manuel.teambuilting.model.PlayerToTeam;
import org.manuel.teambuilting.model.repository.PlayerRepository;
import org.manuel.teambuilting.model.repository.PlayerToTeamRepository;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {

	private final PlayerRepository playerRepository;
	private final PlayerToTeamRepository playerToTeamRepository;
	private final DTOSConverter dtosConverter;

	@Inject
	public PlayerService(final PlayerRepository playerRepository, final PlayerToTeamRepository playerToTeamRepository,
			final DTOSConverter dtosConverter) {
		this.playerRepository = playerRepository;
		this.playerToTeamRepository = playerToTeamRepository;
		this.dtosConverter = dtosConverter;
	}

	public PlayerDTO getPlayer(final PlayerId playerId) {
		final Player player = playerRepository.findOne(playerId.getId());
		return dtosConverter.toPlayerDTO(player);
	}

	public PlayerDTO savePlayer(final PlayerDTO player) {
		final Player created = playerRepository.save(dtosConverter.toPlayer(player));
		return dtosConverter.toPlayerDTO(created);
	}

	public Set<PlayerDTO> findPlayerByName(final String name) {
		final Set<Player> players = playerRepository.findByNameLikeIgnoreCase(name);
		return players.stream().map(player -> dtosConverter.toPlayerDTO(player)).collect(Collectors.toSet());
	}

	public Collection<PlayerToTeamDTO> findPlayerHistory(final PlayerId playerId) {
		final Collection<PlayerToTeam> allTeamsPlayerHasPlayed = playerToTeamRepository
				.findByPlayerId(playerId.getId());
		return allTeamsPlayerHasPlayed.stream().map(playerToTeam -> dtosConverter.toPlayerToTeamDTO(playerToTeam))
				.collect(Collectors.toSet());
	}

}
