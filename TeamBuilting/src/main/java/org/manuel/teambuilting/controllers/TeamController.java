/**
 * 
 */
package org.manuel.teambuilting.controllers;

import org.manuel.teambuilting.dtos.Team;
import org.manuel.teambuilting.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
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

	@Autowired
	public TeamController(final TeamService teamService) {
		this.teamService = teamService;
	}
	
	@RequestMapping(method=RequestMethod.POST)
    public Team saveTeam(@RequestParam(value="team", required=true) final Team team) {
		Assert.notNull(team);
        return teamService.saveTeam(team);
    }

}
