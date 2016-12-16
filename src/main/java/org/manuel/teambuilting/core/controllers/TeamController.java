/**
 * 
 */
package org.manuel.teambuilting.core.controllers;

import com.auth0.spring.security.api.Auth0JWTToken;

import java.time.LocalDate;
import java.util.Set;

import javax.inject.Inject;
import javax.validation.Valid;

import org.manuel.teambuilting.core.config.Auth0Client;
import org.manuel.teambuilting.core.model.Player;
import org.manuel.teambuilting.core.model.Team;
import org.manuel.teambuilting.core.model.TeamId;
import org.manuel.teambuilting.core.services.PlayerToTeamService;
import org.manuel.teambuilting.core.services.TeamCommandService;
import org.manuel.teambuilting.core.services.TeamQueryService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
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
	
	private final TeamCommandService teamCommandService;
	private final TeamQueryService teamQueryService;

	private final PlayerToTeamService playerToTeamService;

	private final Auth0Client auth0Client;


	@Inject
	public TeamController(final TeamCommandService teamCommandService, final TeamQueryService teamQueryService, final PlayerToTeamService playerToTeamService, final Auth0Client auth0Client) {
		this.teamCommandService = teamCommandService;
		this.teamQueryService = teamQueryService;
		this.playerToTeamService = playerToTeamService;
		this.auth0Client = auth0Client;
	}

	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	public Team saveTeam(@Valid @RequestBody final Team team) {
		return teamCommandService.createTeam(team);
    }

	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public Team getTeamOf(@PathVariable("id") final TeamId id) {
		return teamQueryService.getTeam(id);
	}

	@RequestMapping(method = RequestMethod.GET)
	public Set<Team> findTeamBy(@RequestParam(value = "sport", defaultValue = "") final String sport,
			@RequestParam(value = "name", defaultValue = "") final String name) {
		return teamQueryService.findTeamBy(sport, name);
	}

	@RequestMapping(path = "/{teamId}/players", method = RequestMethod.GET)
	public Set<Player> getPlayersForTeam(@PathVariable("teamId") final TeamId teamId,
			@RequestParam(value = "date", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") final LocalDate date) {
		Assert.notNull(teamId);
		return playerToTeamService.getPlayersFor(teamId, date);
	}

	/**
	 *  Simple illustration only
	 */
	private void adminChecks(final Auth0JWTToken principal) {
		for(final GrantedAuthority grantedAuthority: principal.getAuthorities()) {
			final String authority = grantedAuthority.getAuthority();
			if (("ROLE_ADMIN".equals(authority))) {
				// just a simple callout to demonstrate role based authorization at service level
				// non-Admin user would be rejected trying to call this service
			}
		}
	}

}
