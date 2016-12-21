package org.manuel.teambuilting.core.services;

import java.util.Set;

import javax.inject.Inject;

import org.manuel.teambuilting.core.model.PlayerId;
import org.manuel.teambuilting.core.model.PlayerToTeamSportDetails;
import org.manuel.teambuilting.core.model.repository.PlayerToTeamSportDetailsRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

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

	public PlayerToTeamSportDetails findPlayerDetailsForSport(final PlayerId playerId, final String sport) {
		return playerToTeamSportDetailsRepository.findByPlayerIdAndSportIgnoringCase(playerId.getId(), sport);
	}

	@PreAuthorize("hasAuthority('user') or hasAuthority('admin')")
	public PlayerToTeamSportDetails savePlayerDetails(final PlayerToTeamSportDetails playerDetails) {
		return playerToTeamSportDetailsRepository.save(playerDetails);
	}

}
