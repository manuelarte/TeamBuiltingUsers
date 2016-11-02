/**
 * 
 */
package org.manuel.teambuilting.controllers;

import java.time.LocalDate;
import java.util.Set;

import javax.inject.Inject;

import org.manuel.teambuilting.dtos.PlayerDTO;
import org.manuel.teambuilting.dtos.TeamHistDTO;
import org.manuel.teambuilting.model.TeamId;
import org.manuel.teambuilting.services.PlayerToTeamService;
import org.manuel.teambuilting.services.TeamHistService;
import org.manuel.teambuilting.services.TeamService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Manuel Doncel Martos
 *
 */
@RestController
@RequestMapping("/teams")
public class TeamController {
	
	private final TeamService teamService;
	private final TeamHistService teamHistService;
	private final PlayerToTeamService playerToTeamService;

	@Inject
	public TeamController(final TeamService teamService, final TeamHistService teamHistService,
			final PlayerToTeamService playerToTeamService) {
		this.teamService = teamService;
		this.teamHistService = teamHistService;
		this.playerToTeamService = playerToTeamService;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public Set<TeamHistDTO> findTeamByName(@RequestParam(value = "name", defaultValue = "") final String name) {
		Assert.notNull(name);
		return teamHistService.findTeamByName(name);
	}

	@RequestMapping(method = RequestMethod.POST)
	public TeamHistDTO saveTeam(@RequestBody final TeamHistDTO team) {
		Assert.notNull(team);
		return teamService.createTeam(team);
    }

	@RequestMapping(path = "/{teamId}", method = RequestMethod.GET)
	public TeamHistDTO getLastTeamHistOf(@PathVariable("teamId") final TeamId teamId) {
		Assert.notNull(teamId);
		return teamHistService.getLastTeamHist(teamId);
	}

	@RequestMapping(path = "/{teamId}/players", method = RequestMethod.GET)
	public Set<PlayerDTO> getPlayersForTeam(@PathVariable("teamId") final TeamId teamId,
			@RequestParam(value = "date", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") final LocalDate date) {
		Assert.notNull(teamId);
		return playerToTeamService.getPlayersFor(teamId, date);
	}

}
