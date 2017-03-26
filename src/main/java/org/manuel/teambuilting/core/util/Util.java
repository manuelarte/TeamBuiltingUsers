package org.manuel.teambuilting.core.util;

import com.auth0.authentication.result.UserProfile;
import com.auth0.spring.security.api.Auth0JWTToken;
import org.manuel.teambuilting.core.config.Auth0Client;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.Optional;

/**
 * @author manuel.doncel.martos
 * @since 11-3-2017
 */
@Component
public class Util {

	private final Auth0Client auth0Client;

	@Inject
	public Util(final Auth0Client auth0Client) {
		this.auth0Client = auth0Client;
	}

	public Optional<UserProfile> getUserProfile() {
		final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return auth instanceof Auth0JWTToken ? Optional.of(auth0Client.getUser((Auth0JWTToken) auth)) : Optional.empty();
	}
}
