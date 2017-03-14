/**
 * 
 */
package org.manuel.teambuilting.core.controllers.command;

import com.auth0.spring.security.api.Auth0JWTToken;

import javax.inject.Inject;
import javax.validation.Valid;

import org.manuel.teambuilting.core.config.Auth0Client;
import org.manuel.teambuilting.core.model.Team;
import org.manuel.teambuilting.core.services.command.TeamCommandService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Manuel Doncel Martos
 *
 */
@RestController
@RequestMapping("/core/teams")
public class TeamCommandController {
	
	private final TeamCommandService teamCommandService;
	private final Auth0Client auth0Client;


	@Inject
	public TeamCommandController(final TeamCommandService teamCommandService, final Auth0Client auth0Client) {
		this.teamCommandService = teamCommandService;
		this.auth0Client = auth0Client;
	}

	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	public Team saveTeam(@Valid @RequestBody final Team team) {
		return teamCommandService.save(team);
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