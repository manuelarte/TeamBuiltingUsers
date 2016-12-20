package org.manuel.teambuilting.core.services;

import org.manuel.teambuilting.core.model.PlayerId;
import org.manuel.teambuilting.core.model.PlayerToTeamSportDetails;
import org.manuel.teambuilting.core.model.repository.PlayerToTeamSportDetailsRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Set;

/**
 * @author Manuel Doncel Martos
 *
 */
@Service
public class PlayerToTeamSportDetailsService {

	private final PlayerToTeamSportDetailsRepository playerToTeamSportDetailsRepository;

	@Inject
	public PlayerToTeamSportDetailsService(final PlayerToTeamSportDetailsRepository playerToTeamSportDetailsRepository) {
		this.playerToTeamSportDetailsRepository = playerToTeamSportDetailsRepository;
	}

	public Set<PlayerToTeamSportDetails> findPlayerDetails(final PlayerId playerId) {
		return playerToTeamSportDetailsRepository.findByPlayerId(playerId.getId());
	}

	@PreAuthorize("hasAuthority('user') or hasAuthority('admin')")
	public PlayerToTeamSportDetails savePlayerDetails(final PlayerToTeamSportDetails playerDetails) {
		return playerToTeamSportDetailsRepository.save(playerDetails);
	}

}
