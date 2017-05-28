package org.manuel.teambuilting.users.services.query.impl;

import com.auth0.client.auth.AuthAPI;
import com.auth0.json.auth.TokenHolder;
import com.auth0.net.AuthRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import lombok.SneakyThrows;
import org.manuel.teambuilting.users.config.Auth0Properties;
import org.manuel.teambuilting.users.model.User;
import org.manuel.teambuilting.users.services.query.UserQueryService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import java.text.MessageFormat;
import java.util.Optional;

/**
 * @author manuel.doncel.martos
 * @since 29-3-2017
 */
@SuppressWarnings("unnused")
@Service
class UserQueryServiceImpl implements UserQueryService {

	private final String usersExtension;
	private final Auth0Properties auth0Properties;
	private final ObjectMapper mapper;
	private final AuthAPI authAPI;

	private Optional<TokenHolder> tokenHolder;

	public UserQueryServiceImpl(final @Value("${resources.users}") String usersExtension,
								final AuthAPI authAPI, final Auth0Properties auth0Properties) {
		this.usersExtension = usersExtension;
		this.auth0Properties = auth0Properties;
		this.authAPI = authAPI;
		this.mapper = new ObjectMapper();
	}

	@PostConstruct
    @SneakyThrows
	private void getToken() {
        final AuthRequest authRequest = authAPI.requestToken("https://manuelarte.eu.auth0.com/api/v2/");
        tokenHolder = Optional.of(authRequest.execute());
    }

	@Override
	@SneakyThrows
	public Optional<User> findById(final String id) {
		Assert.hasLength(id);
		final String usersUrl = MessageFormat.format("https://{0}/{1}/{2}", auth0Properties.getDomain(), usersExtension, id);
		final HttpResponse<String> user = Unirest.get(usersUrl)
				.header("authorization",
						MessageFormat.format("Bearer {0}", tokenHolder.get().getAccessToken()))
				.asString();
		return Optional.ofNullable(mapper.readValue(user.getBody(), User.class));
	}
}
