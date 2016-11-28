package org.manuel.teambuilting.core.services;

import javax.inject.Inject;

import org.manuel.teambuilting.core.dtos.PlayerToTeamSportDetailsDTO;
import org.manuel.teambuilting.core.model.PlayerId;
import org.manuel.teambuilting.core.model.PlayerToTeamSportDetails;
import org.manuel.teambuilting.core.model.repository.PlayerToTeamSportDetailsRepository;
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
	public PlayerToTeamSportDetailsService(final PlayerToTeamSportDetailsRepository playerToTeamSportDetailsRepository,
			final DTOSConverter dtosConverter) {
		this.playerToTeamSportDetailsRepository = playerToTeamSportDetailsRepository;
		this.dtosConverter = dtosConverter;
	}

	public PlayerToTeamSportDetailsDTO findPlayerDetails(final PlayerId playerId, final String sport) {
		final PlayerToTeamSportDetails playerDetails = playerToTeamSportDetailsRepository
				.findByPlayerIdAndSportLikeIgnoreCase(playerId.getId(), sport);

		return dtosConverter.toPlayerToTeamSportDetailsDTO(playerDetails);
	}

	public PlayerToTeamSportDetailsDTO savePlayerDetails(final PlayerToTeamSportDetailsDTO playerDetails) {
		final PlayerToTeamSportDetails saved = playerToTeamSportDetailsRepository
				.save(dtosConverter.toPlayerToTeamSportDetails(playerDetails));
		return dtosConverter.toPlayerToTeamSportDetailsDTO(saved);
	}

}
