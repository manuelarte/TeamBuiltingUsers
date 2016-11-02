/**
 * 
 */
package org.manuel.teambuilting.services;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.manuel.teambuilting.dtos.PlayerDTO;
import org.manuel.teambuilting.model.Player;
import org.manuel.teambuilting.model.PlayerToTeam;
import org.manuel.teambuilting.model.TeamId;
import org.manuel.teambuilting.model.repository.PlayerRepository;
import org.manuel.teambuilting.model.repository.PlayerToTeamRepository;
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
			final PlayerRepository playerRepository, final DTOSConverter dtosConverter) {
		this.playerToTeamRepository = playerToTeamRepository;
		this.playerRepository = playerRepository;
		this.dtosConverter = dtosConverter;
	}

	public Set<PlayerDTO> getPlayersFor(final TeamId teamId, final LocalDate time) {
		final Collection<PlayerToTeam> playersForTeam = playerToTeamRepository
				.findByEndDateAfterOrEndDateIsNullAndTeamId(time, teamId.getId());
		final Set<Player> players = playersForTeam.stream()
				.map(playerId -> playerRepository.findOne(playerId.getPlayerId())).collect(Collectors.toSet());
		return players.stream().map(dtosConverter.toPlayerDTO()).collect(Collectors.toSet());
	}

}
