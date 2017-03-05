package org.manuel.teambuilting.core.aspects;

import com.auth0.authentication.result.UserProfile;
import com.auth0.spring.security.api.Auth0JWTToken;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.manuel.teambuilting.core.config.Auth0Client;
import org.manuel.teambuilting.core.model.Player;
import org.manuel.teambuilting.core.model.Team;
import org.manuel.teambuilting.core.model.UserData;
import org.manuel.teambuilting.core.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

/**
 * @author manuel.doncel.martos
 * @since 9-1-2017
 */
@Aspect
@Component
public class UserDataAspect {

	private final UserService userService;
	private final Auth0Client auth0Client;

	@Inject
	public UserDataAspect(final UserService userService, final Auth0Client auth0Client) {
		this.userService = userService;
		this.auth0Client = auth0Client;
	}

	@AfterReturning(
		pointcut="@annotation(org.manuel.teambuilting.core.aspects.UserDataSave)",
		returning="retVal")
	public void saveEntityToUserData(final JoinPoint call, Object retVal) {
		final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		final UserProfile user = auth0Client.getUser((Auth0JWTToken) auth);
		final UserData userData = userService.getOrCreateUserData(user.getId());
		if (retVal instanceof Player) {
			userData.setPlayerId(((Player) retVal).getId());
		} else if (retVal instanceof Team) {
			userData.addTeamAdminByUser(((Team) retVal).getId());
		}
		userService.update(userData);
	}

	@After(value = "@annotation(org.manuel.teambuilting.core.aspects.UserDataDeletePlayer)")
	public void deletePlayerFromUserData(final JoinPoint call) {
		final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		final UserProfile user = auth0Client.getUser((Auth0JWTToken) auth);
		final UserData userData = userService.getOrCreateUserData(user.getId());
		userData.setPlayerId(null);
		userService.update(userData);
	}

}
