/**
 * 
 */
package org.manuel.teambuilting.core.controllers;

import java.time.LocalDate;
import java.util.Set;

import javax.inject.Inject;
import javax.validation.Valid;

import org.manuel.teambuilting.core.dtos.PlayerDTO;
import org.manuel.teambuilting.core.dtos.TeamDTO;
import org.manuel.teambuilting.core.dtos.TeamHistDTO;
import org.manuel.teambuilting.core.model.TeamId;
import org.manuel.teambuilting.core.services.PlayerToTeamService;
import org.manuel.teambuilting.core.services.TeamHistService;
import org.manuel.teambuilting.core.services.TeamService;
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
	
	@RequestMapping(path = "/newTeam", method = RequestMethod.POST, produces = "application/json")
	public TeamDTO createTeam(@Valid @RequestBody final TeamDTO team) {
		Assert.notNull(team);
		return teamService.createTeam(team);
	}

	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	public TeamHistDTO saveTeamHist(@Valid @RequestBody final TeamHistDTO teamhist) {
		Assert.notNull(teamhist);
		return teamHistService.saveTeamHist(teamhist);
    }

	@RequestMapping(path = "/{teamId}", method = RequestMethod.GET)
	public TeamHistDTO getLastTeamHistOf(@PathVariable("teamId") final TeamId teamId) {
		Assert.notNull(teamId);
		return teamHistService.getLastTeamHist(teamId);
	}

	@RequestMapping(method = RequestMethod.GET)
	public Set<TeamHistDTO> findTeamBy(@RequestParam(value = "sport", defaultValue = "") final String sport,
			@RequestParam(value = "name", defaultValue = "") final String name) {
		Assert.notNull(name);
		return teamHistService.findTeamBy(sport, name);
	}

	@RequestMapping(path = "/{teamId}/players", method = RequestMethod.GET)
	public Set<PlayerDTO> getPlayersForTeam(@PathVariable("teamId") final TeamId teamId,
			@RequestParam(value = "date", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") final LocalDate date) {
		Assert.notNull(teamId);
		return playerToTeamService.getPlayersFor(teamId, date);
	}

}
