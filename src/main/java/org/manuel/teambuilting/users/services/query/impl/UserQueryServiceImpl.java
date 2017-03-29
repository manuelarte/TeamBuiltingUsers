package org.manuel.teambuilting.users.services.query.impl;

import com.fasterxml.jackson.core.type.TypeReference;
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
import java.util.HashMap;
import java.util.Map;
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

	private Map<String, String> authentication;

	public UserQueryServiceImpl(final @Value("${resources.users}") String usersExtension, final Auth0Properties auth0Properties) {
		this.usersExtension = usersExtension;
		this.auth0Properties = auth0Properties;
		this.mapper = new ObjectMapper();
	}

	@PostConstruct
	@SneakyThrows
	private void getToken() {
		// TODO THE Token expires... add a check to call this method again if it is expired, and do an async call to the token
		// so then we do not have t wait until it finishes.
		final String tokenUrl = MessageFormat.format("https://{0}/{1}", auth0Properties.getDomain(), "oauth/token");
		final Map<String, String> body = new HashMap<>();
		body.put("client_id", auth0Properties.getClientId());
		body.put("client_secret", auth0Properties.getClientSecret());
		body.put("audience", "https://manuelarte.eu.auth0.com/api/v2/");
		body.put("grant_type", "client_credentials");
		final HttpResponse<String> response = Unirest.post(tokenUrl)
				.header("content-type", "application/json")
				//.body("{\"client_id\":\"XOBz4RdzWoMnpxAvXKtK9R8W9IODYKsl\",\"client_secret\":\"tvKvKZd1tigVIAGztcOELwKIj0B0DswEbLdRG1PWu7NfZXk6VGbGkWdQjFpTZmWp\",\"audience\":\"https://manuelarte.eu.auth0.com/api/v2/\",\"grant_type\":\"client_credentials\"}")
				.body(mapper.writeValueAsString(body))
				.asString();

		authentication = mapper.readValue(response.getBody(), new TypeReference<Map<String, String>>(){});
	}

	@Override
	@SneakyThrows
	public Optional<User> findById(final String id) {
		Assert.hasLength(id);
		final String usersUrl = MessageFormat.format("https://{0}/{1}/{2}", auth0Properties.getDomain(), usersExtension, id);
		final HttpResponse<String> user = Unirest.get(usersUrl)
				.header("authorization",
						MessageFormat.format("Bearer {0}", authentication.get("access_token")))
				.asString();
		return Optional.ofNullable(mapper.readValue(user.getBody(), User.class));
	}
}
