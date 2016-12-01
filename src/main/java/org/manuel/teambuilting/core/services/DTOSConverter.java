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
import org.manuel.teambuilting.core.dtos.TeamDTO;
import org.manuel.teambuilting.core.dtos.TeamHistDTO;
import org.manuel.teambuilting.core.model.ModelId;
import org.manuel.teambuilting.core.model.Player;
import org.manuel.teambuilting.core.model.PlayerId;
import org.manuel.teambuilting.core.model.PlayerToTeam;
import org.manuel.teambuilting.core.model.PlayerToTeamId;
import org.manuel.teambuilting.core.model.PlayerToTeamSportDetails;
import org.manuel.teambuilting.core.model.Team;
import org.manuel.teambuilting.core.model.TeamHist;
import org.manuel.teambuilting.core.model.TeamHistId;
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
				.teamId(playerToTeam.getTeamId().getId()).startDate(playerToTeam.getStartDate())
				.endDate(playerToTeam.getEndDate()).build();
	}

	public PlayerToTeamDTO toPlayerToTeamDTO(final PlayerToTeam playerToTeam) {
		return PlayerToTeamDTO.builder().id(new PlayerToTeamId(playerToTeam.getId()))
				.playerId(new PlayerId(playerToTeam.getPlayerId()))
				.teamId(new TeamId(playerToTeam.getTeamId())).startDate(playerToTeam.getStartDate())
				.endDate(playerToTeam.getEndDate()).build();
	}

	public TeamHist createTeamHist(final TeamHistDTO teamHist) {
		return TeamHist.builder().id(getIdNullSafe(teamHist.getId())).teamId(teamHist.getTeamId().getId())
				.name(teamHist.getName())
				.location(teamHist.getLocation()).fromDate(teamHist.getFromDate())
				.toDate(teamHist.getToDate()).build();
	}

	public TeamHistDTO createTeamHistDTO(final TeamHist teamHist, final Team team) {
		return TeamHistDTO.builder().id(new TeamHistId(teamHist.getId())).teamId(new TeamId(teamHist.getTeamId()))
				.name(teamHist.getName()).sport(team.getSport()).location(teamHist.getLocation())
				.fromDate(teamHist.getFromDate()).toDate(teamHist.getToDate())
				.build();
	}

	public Team createTeam(final TeamDTO team) {
		return Team.builder().id(getIdNullSafe(team.getId())).sport(team.getSport()).build();
	}

	public TeamDTO createTeamDTO(final Team team) {
		return TeamDTO.builder().id(new TeamId(team.getId())).sport(team.getSport()).build();
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