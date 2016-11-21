/**
 * 
 */
package org.manuel.teambuilting.services;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.manuel.teambuilting.dtos.PlayerDTO;
import org.manuel.teambuilting.dtos.PlayerToTeamDTO;
import org.manuel.teambuilting.dtos.PlayerToTeamSportDetailsDTO;
import org.manuel.teambuilting.dtos.TeamDTO;
import org.manuel.teambuilting.dtos.TeamHistDTO;
import org.manuel.teambuilting.model.Player;
import org.manuel.teambuilting.model.PlayerId;
import org.manuel.teambuilting.model.PlayerToTeam;
import org.manuel.teambuilting.model.PlayerToTeamId;
import org.manuel.teambuilting.model.PlayerToTeamSportDetails;
import org.manuel.teambuilting.model.Team;
import org.manuel.teambuilting.model.TeamHist;
import org.manuel.teambuilting.model.TeamHistId;
import org.manuel.teambuilting.model.TeamId;
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
		return Player.builder().name(player.getName()).nickname(player.getNickname())
				.sex(player.getSex()).bornAddress(player.getBornAddress()).build();
	}

	public PlayerDTO toPlayerDTO(final Player player) {
		return PlayerDTO.builder().id(new PlayerId(player.getId())).name(player.getName())
				.nickname(player.getNickname()).sex(player.getSex()).bornAddress(player.getBornAddress()).build();
	}

	public PlayerToTeam toPlayerToTeam(final PlayerToTeamDTO playerToTeam) {
		return PlayerToTeam.builder().id(playerToTeam.getId().getId()).playerId(playerToTeam.getPlayerId().getId())
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
		return TeamHist.builder().teamId(teamHist.getTeamId().getId()).name(teamHist.getName())
				.location(teamHist.getLocation()).emblemPath(teamHist.getEmblemPath()).fromDate(teamHist.getFromDate())
				.toDate(teamHist.getToDate()).build();
	}

	public TeamHistDTO createTeamHistDTO(final TeamHist teamHist, final Team team) {
		return TeamHistDTO.builder().id(new TeamHistId(teamHist.getId())).teamId(new TeamId(teamHist.getTeamId()))
				.name(teamHist.getName()).sport(team.getSport()).location(teamHist.getLocation())
				.emblemPath(teamHist.getEmblemPath()).fromDate(teamHist.getFromDate()).toDate(teamHist.getToDate())
				.build();
	}

	public Team createTeam(final TeamDTO team) {
		return Team.builder().sport(team.getSport()).build();
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

}