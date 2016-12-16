/**
 * 
 */
package org.manuel.teambuilting.core.services;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.manuel.teambuilting.core.dtos.PlayerDTO;
import org.manuel.teambuilting.core.dtos.PlayerToTeamDTO;
import org.manuel.teambuilting.core.dtos.PlayerToTeamSportDetailsDTO;
import org.manuel.teambuilting.core.model.ModelId;
import org.manuel.teambuilting.core.model.Player;
import org.manuel.teambuilting.core.model.PlayerId;
import org.manuel.teambuilting.core.model.PlayerToTeam;
import org.manuel.teambuilting.core.model.PlayerToTeamId;
import org.manuel.teambuilting.core.model.PlayerToTeamSportDetails;
import org.manuel.teambuilting.core.model.TeamId;
import org.springframework.stereotype.Component;

/**
 * @author Manuel Doncel Martos
 *
 */
@Component
public class DTOSConverter {

	public Set<String> convertPlayerIdsToString(final Collection<PlayerId> playerIds) {
		return playerIds.stream().map(player -> player.getId()).collect(Collectors.toSet());
	}

	public Set<PlayerId> convertPlayerIdStringToPlayerId(final Collection<String> playerIds) {
		return playerIds.stream().map(playerId -> new PlayerId(playerId)).collect(Collectors.toSet());
	}


	public Set<PlayerDTO> convertPlayers(final Collection<Player> players) {
		return players.stream().map(player -> toPlayerDTO(player)).collect(Collectors.toSet());
	}

	public Player toPlayer(final PlayerDTO player) {
		return Player.builder().id(getIdNullSafe(player.getId())).name(player.getName()).nickname(player.getNickname())
				.sex(player.getSex()).bornAddress(player.getBornAddress()).build();
	}

	public PlayerDTO toPlayerDTO(final Player player) {
		return PlayerDTO.builder().id(new PlayerId(player.getId())).name(player.getName())
				.nickname(player.getNickname()).sex(player.getSex()).bornAddress(player.getBornAddress()).build();
	}

	public PlayerToTeam toPlayerToTeam(final PlayerToTeamDTO playerToTeam) {
		return PlayerToTeam.builder().id(getIdNullSafe(playerToTeam.getId()))
				.playerId(playerToTeam.getPlayerId().getId())
				.teamId(playerToTeam.getTeamId().getId()).startDate(playerToTeam.getFromDate())
				.endDate(playerToTeam.getToDate()).build();
	}

	public PlayerToTeamDTO toPlayerToTeamDTO(final PlayerToTeam playerToTeam) {
		return PlayerToTeamDTO.builder().id(new PlayerToTeamId(playerToTeam.getId()))
				.playerId(new PlayerId(playerToTeam.getPlayerId()))
				.teamId(new TeamId(playerToTeam.getTeamId())).fromDate(playerToTeam.getStartDate())
				.toDate(playerToTeam.getEndDate()).build();
	}

	public PlayerToTeamSportDetailsDTO toPlayerToTeamSportDetailsDTO(final PlayerToTeamSportDetails playerToTeamSportDetails) {
		return PlayerToTeamSportDetailsDTO.builder().id(playerToTeamSportDetails.getId())
				.playerId(playerToTeamSportDetails.getPlayerId())
				.sport(playerToTeamSportDetails.getSport()).bio(playerToTeamSportDetails.getBio())
				.mainPosition(playerToTeamSportDetails.getMainPosition())
				.otherPositions(playerToTeamSportDetails.getOtherPositions()).build();
	}

	public PlayerToTeamSportDetails toPlayerToTeamSportDetails(
			final PlayerToTeamSportDetailsDTO playerToTeamSportDetails) {
		return PlayerToTeamSportDetails.builder().id(playerToTeamSportDetails.getId())
				.playerId(playerToTeamSportDetails.getPlayerId()).sport(playerToTeamSportDetails.getSport())
				.bio(playerToTeamSportDetails.getBio()).mainPosition(playerToTeamSportDetails.getMainPosition())
				.otherPositions(playerToTeamSportDetails.getOtherPositions()).build();
	}

	private String getIdNullSafe(final ModelId modelId) {
		return modelId != null ? modelId.getId() : null;
	}

}