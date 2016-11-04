/**
 * 
 */
package org.manuel.teambuilting.services;

import java.util.Collection;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.manuel.teambuilting.dtos.PlayerDTO;
import org.manuel.teambuilting.dtos.PlayerToTeamDTO;
import org.manuel.teambuilting.dtos.TeamDTO;
import org.manuel.teambuilting.dtos.TeamHistDTO;
import org.manuel.teambuilting.model.Player;
import org.manuel.teambuilting.model.PlayerId;
import org.manuel.teambuilting.model.PlayerToTeam;
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
		return players.stream().map(toPlayerDTO()).collect(Collectors.toSet());
	}

	public Function<PlayerDTO, Player> toPlayer() {
		return player -> Player.builder().name(player.getName()).nickname(player.getNickname())
				.bornAddress(player.getBornAddress()).build();
	}

	public Function<Player, PlayerDTO> toPlayerDTO() {
		return player -> PlayerDTO.builder().id(new PlayerId(player.getId())).name(player.getName())
				.nickname(player.getNickname()).bornAddress(player.getBornAddress()).build();
	}

	public Function<PlayerToTeamDTO, PlayerToTeam> toPlayerToTeam() {
		return playerToTeam -> PlayerToTeam.builder().playerId(playerToTeam.getPlayerId().getId())
				.teamId(playerToTeam.getTeamId().getId()).startDate(playerToTeam.getStartDate())
				.endDate(playerToTeam.getEndDate()).build();
	}

	public Function<TeamHistDTO, TeamHist> createTeamHist() {
		return teamHist -> TeamHist.builder().teamId(teamHist.getTeamId().getId()).name(teamHist.getName())
				.location(teamHist.getLocation()).emblemPath(teamHist.getEmblemPath()).fromDate(teamHist.getFromDate())
				.toDate(teamHist.getToDate()).build();
	}

	public Function<TeamHist, TeamHistDTO> createTeamHistDTO() {
		return teamHist -> TeamHistDTO.builder().id(new TeamHistId(teamHist.getId()))
				.teamId(new TeamId(teamHist.getTeamId())).name(teamHist.getName()).location(teamHist.getLocation())
				.emblemPath(teamHist.getEmblemPath()).fromDate(teamHist.getFromDate()).toDate(teamHist.getToDate())
				.build();
	}

	public Team createTeam(final TeamDTO team) {
		return Team.builder().id(team.getId().getId()).sport(team.getSport()).build();
	}

}