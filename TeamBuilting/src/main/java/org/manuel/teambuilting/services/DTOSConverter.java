/**
 * 
 */
package org.manuel.teambuilting.services;

import java.util.Collection;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.manuel.teambuilting.dtos.PlayerDTO;
import org.manuel.teambuilting.dtos.TeamDTO;
import org.manuel.teambuilting.model.Player;
import org.manuel.teambuilting.model.PlayerId;
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
		return team -> new Team.Builder().withName(team.getName()).withPlayers(convertPlayersDTO(team.getPlayers()))
				.build();
	}

	public Function<Team, TeamDTO> convertTeam() {
		return team -> new TeamDTO.Builder().withId(new TeamId(team.getId())).withName(team.getName())
				.withPlayers(convertPlayers(team.getPlayers())).build();
	}

	private Set<Player> convertPlayersDTO(final Collection<PlayerDTO> players) {
		return players.stream().map(toPlayer()).collect(Collectors.toSet());
	}

	public Set<PlayerDTO> convertPlayers(final Collection<Player> players) {
		return players.stream().map(toPlayerDTO()).collect(Collectors.toSet());
	}

	public Function<PlayerDTO, Player> toPlayer() {
		return player -> new Player.Builder().withName(player.getName()).withAddress(player.getAddress()).build();
	}

	public Function<Player, PlayerDTO> toPlayerDTO() {
		return player -> new PlayerDTO.Builder().withId(new PlayerId(player.getId())).withName(player.getName())
				.withAddress(player.getAddress()).build();
	}

}
