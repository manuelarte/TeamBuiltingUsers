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
import org.manuel.teambuilting.model.Player;
import org.manuel.teambuilting.model.PlayerId;
import org.manuel.teambuilting.model.PlayerToTeam;
import org.manuel.teambuilting.model.Team;
import org.manuel.teambuilting.model.TeamId;
import org.springframework.stereotype.Component;

/**
 * @author Manuel Doncel Martos
 *
 */
@Component
public class DTOSConverter {

	public Function<TeamDTO, Team> convertTeamDTO() {
		return team -> new Team.Builder().withName(team.getName()).build();
	}

	public Function<Team, TeamDTO> convertTeam() {
		return team -> new TeamDTO.Builder().withId(new TeamId(team.getId())).withName(team.getName()).build();
	}

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

}
