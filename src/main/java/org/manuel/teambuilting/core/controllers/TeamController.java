/**
 * 
 */
package org.manuel.teambuilting.core.controllers;

import com.auth0.authentication.result.UserProfile;
import com.auth0.spring.security.api.Auth0JWTToken;
import org.manuel.teambuilting.core.config.Auth0Client;
import org.manuel.teambuilting.core.model.Player;
import org.manuel.teambuilting.core.model.Team;
import org.manuel.teambuilting.core.model.TeamId;
import org.manuel.teambuilting.core.services.PlayerToTeamService;
import org.manuel.teambuilting.core.services.TeamCommandService;
import org.manuel.teambuilting.core.services.TeamQueryService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

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
		final Optional<UserProfile> userProfile = getUserProfile();
		return teamQueryService.getTeam(id, userProfile);
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

	public Optional<UserProfile> getUserProfile() {
		final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return auth instanceof Auth0JWTToken ? Optional.of(auth0Client.getUser((Auth0JWTToken) auth)) : Optional.empty();
	}
}
