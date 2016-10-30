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
		return player -> new Player.Builder().withName(player.getName()).withBornAddress(player.getBornAddress())
				.build();
	}

	public Function<Player, PlayerDTO> toPlayerDTO() {
		return player -> new PlayerDTO.Builder().withId(new PlayerId(player.getId())).withName(player.getName())
				.withBornAddress(player.getBornAddress()).build();
	}

	public Function<PlayerToTeamDTO, PlayerToTeam> toPlayerToTeam() {
		return playerToTeam -> new PlayerToTeam.Builder().withPlayerId(playerToTeam.getPlayerId().getId())
				.withTeamId(playerToTeam.getTeamId().getId())
				.withStartDate(playerToTeam.getStartDate()).withEndDate(playerToTeam.getEndDate()).build();
	}

	public Function<TeamHistDTO, TeamHist> createTeamHist() {
		return teamHist -> new TeamHist.Builder().withTeamId(teamHist.getTeamId().getId()).withName(teamHist.getName())
				.withLocation(teamHist.getLocation())
				.withFromDate(teamHist.getFromDate()).withToDate(teamHist.getToDate()).build();
	}

	public Function<TeamHist, TeamHistDTO> createTeamHistDTO() {
		return teamHist -> new TeamHistDTO.Builder().withId(new TeamHistId(teamHist.getId()))
				.withTeamId(new TeamId(teamHist.getTeamId())).withName(teamHist.getName())
				.withLocation(teamHist.getLocation())
				.withFromDate(teamHist.getFromDate()).withToDate(teamHist.getToDate()).build();
	}

	public Team createTeam(final TeamDTO team) {
		return new Team.Builder().withId(team.getId().getId()).build();
	}

}
