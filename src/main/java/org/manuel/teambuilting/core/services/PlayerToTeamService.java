/**
 * 
 */
package org.manuel.teambuilting.core.services;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.manuel.teambuilting.core.dtos.PlayerDTO;
import org.manuel.teambuilting.core.dtos.PlayerToTeamDTO;
import org.manuel.teambuilting.core.model.Player;
import org.manuel.teambuilting.core.model.PlayerId;
import org.manuel.teambuilting.core.model.PlayerToTeam;
import org.manuel.teambuilting.core.model.TeamId;
import org.manuel.teambuilting.core.model.repository.PlayerRepository;
import org.manuel.teambuilting.core.model.repository.PlayerToTeamRepository;
import org.springframework.stereotype.Service;

/**
 * @author Manuel Doncel Martos
 *
 */
@Service
public class PlayerToTeamService {

	private final PlayerToTeamRepository playerToTeamRepository;
	private final PlayerRepository playerRepository;
	private final DTOSConverter dtosConverter;

	@Inject
	public PlayerToTeamService(final PlayerToTeamRepository playerToTeamRepository,
			final PlayerRepository playerRepository,
			final DTOSConverter dtosConverter) {
		this.playerToTeamRepository = playerToTeamRepository;
		this.playerRepository = playerRepository;
		this.dtosConverter = dtosConverter;
	}

	public Set<PlayerDTO> getPlayersFor(final TeamId teamId, final LocalDate time) {
		final Collection<PlayerToTeam> playersForTeam = playerToTeamRepository
				.findByEndDateAfterOrEndDateIsNullAndTeamId(time, teamId.getId());
		final Set<Player> players = playersForTeam.stream()
				.map(playerId -> playerRepository.findOne(playerId.getPlayerId())).collect(Collectors.toSet());
		return players.stream().map(player -> dtosConverter.toPlayerDTO(player)).collect(Collectors.toSet());
	}

	public Collection<PlayerToTeamDTO> findPlayerHistory(final PlayerId playerId) {
		final Collection<PlayerToTeam> allTeamsPlayerHasPlayed = playerToTeamRepository
				.findByPlayerId(playerId.getId());
		return allTeamsPlayerHasPlayed.stream().map(playerToTeam -> dtosConverter.toPlayerToTeamDTO(playerToTeam))
				.collect(Collectors.toSet());
	}

	public PlayerToTeamDTO savePlayerToTeam(final PlayerToTeamDTO playerToTeamDTO) {
		final PlayerToTeam saved = playerToTeamRepository.save(dtosConverter.toPlayerToTeam(playerToTeamDTO));
		return dtosConverter.toPlayerToTeamDTO(saved);
	}

}
