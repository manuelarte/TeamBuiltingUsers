package org.manuel.teambuilting.users.services.query.impl;

import java.util.Optional;

import org.manuel.teambuilting.users.model.User;
import org.manuel.teambuilting.users.services.query.UserQueryService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.AllArgsConstructor;

/**
 * @author manuel.doncel.martos
 * @since 29-3-2017
 */
@Service
@AllArgsConstructor
class UserQueryServiceImpl implements UserQueryService {

	private final RestTemplate restTemplate;

	@Override
	public Optional<User> findById(final String id) {
		return null;
	}
}
