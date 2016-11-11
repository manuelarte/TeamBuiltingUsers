package org.manuel.teambuilting.services;

import javax.inject.Inject;

import org.manuel.teambuilting.dtos.PlayerToTeamSportDetailsDTO;
import org.manuel.teambuilting.model.PlayerId;
import org.manuel.teambuilting.model.PlayerToTeamSportDetails;
import org.manuel.teambuilting.model.repository.PlayerToTeamSportDetailsRepository;
import org.springframework.stereotype.Service;

/**
 * @author Manuel Doncel Martos
 *
 */
@Service
public class PlayerToTeamSportDetailsService {

	private final PlayerToTeamSportDetailsRepository playerToTeamSportDetailsRepository;

	private final DTOSConverter dtosConverter;

	@Inject
	public PlayerToTeamSportDetailsService(
			final PlayerToTeamSportDetailsRepository playerToTeamSportDetailsRepository,
			final DTOSConverter dtosConverter) {
		this.playerToTeamSportDetailsRepository = playerToTeamSportDetailsRepository;
		this.dtosConverter = dtosConverter;
	}

	public PlayerToTeamSportDetailsDTO findPlayerDetails(final PlayerId playerId, final String sport) {
		final PlayerToTeamSportDetails playerDetails = playerToTeamSportDetailsRepository
				.findByPlayerIdAndSportLikeIgnoreCase(playerId.getId(), sport);

		return dtosConverter.toPlayerToTeamSportDetailsDTO(playerDetails);
	}

}
