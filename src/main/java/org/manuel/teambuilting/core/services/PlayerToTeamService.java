/**
 * 
 */
package org.manuel.teambuilting.core.services;

import org.manuel.teambuilting.core.model.*;
import org.manuel.teambuilting.core.repositories.PlayerRepository;
import org.manuel.teambuilting.core.repositories.PlayerToTeamRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

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
				.findByToDateAfterOrToDateIsNullAndTeamId(time, teamId.getId());
		return playersForTeam.stream()
				.map(playerId -> playerRepository.findOne(playerId.getPlayerId())).collect(Collectors.toSet());
	}

	public Collection<PlayerToTeam> findPlayerHistory(final PlayerId playerId) {
		return playerToTeamRepository.findByPlayerId(playerId.getId());
	}

	@PreAuthorize("hasAuthority('user') or hasAuthority('admin')")
	public PlayerToTeam savePlayerToTeam(final PlayerToTeam playerToTeamDTO) {
		return playerToTeamRepository.save(playerToTeamDTO);
	}

	@PreAuthorize("hasAuthority('user') or hasAuthority('admin')")
	public void deletePlayerToTeam(PlayerToTeamId playerToTeamId) {
		playerToTeamRepository.delete(playerToTeamId.getId());
    }
}
