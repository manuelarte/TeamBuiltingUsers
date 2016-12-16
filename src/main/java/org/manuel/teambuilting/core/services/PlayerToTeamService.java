/**
 * 
 */
package org.manuel.teambuilting.core.services;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.manuel.teambuilting.core.model.Player;
import org.manuel.teambuilting.core.model.PlayerId;
import org.manuel.teambuilting.core.model.PlayerToTeam;
import org.manuel.teambuilting.core.model.TeamId;
import org.manuel.teambuilting.core.model.repository.PlayerRepository;
import org.manuel.teambuilting.core.model.repository.PlayerToTeamRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

/**
 * @author Manuel Doncel Martos
 *
 */
@Service
public class PlayerToTeamService {

	private final PlayerToTeamRepository playerToTeamRepository;
	private final PlayerRepository playerRepository;

	@Inject
	public PlayerToTeamService(final PlayerToTeamRepository playerToTeamRepository,
			final PlayerRepository playerRepository) {
		this.playerToTeamRepository = playerToTeamRepository;
		this.playerRepository = playerRepository;
	}

	public Set<Player> getPlayersFor(final TeamId teamId, final LocalDate time) {
		final Collection<PlayerToTeam> playersForTeam = playerToTeamRepository
				.findByEndDateAfterOrEndDateIsNullAndTeamId(time, teamId.getId());
		final Set<Player> players = playersForTeam.stream()
				.map(playerId -> playerRepository.findOne(playerId.getPlayerId())).collect(Collectors.toSet());
		return players.stream().map(player -> player).collect(Collectors.toSet());
	}

	public Collection<PlayerToTeam> findPlayerHistory(final PlayerId playerId) {
		return playerToTeamRepository.findByPlayerId(playerId.getId());
	}

	@PreAuthorize("hasAuthority('user') or hasAuthority('admin')")
	public PlayerToTeam savePlayerToTeam(final PlayerToTeam playerToTeamDTO) {
		return playerToTeamRepository.save(playerToTeamDTO);
	}

}
