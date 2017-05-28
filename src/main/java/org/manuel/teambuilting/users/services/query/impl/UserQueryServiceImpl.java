package org.manuel.teambuilting.users.services.query.impl;

import com.auth0.client.mgmt.ManagementAPI;
import com.auth0.json.mgmt.users.User;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.manuel.teambuilting.users.services.query.UserQueryService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Optional;

/**
 * @author manuel.doncel.martos
 * @since 29-3-2017
 */
@SuppressWarnings("unnused")
@Service
@AllArgsConstructor
class UserQueryServiceImpl implements UserQueryService {

	private final ManagementAPI managementAPI;

	@Override
	@SneakyThrows
	public Optional<User> findById(final String id) {
		Assert.hasLength(id);
		return Optional.ofNullable(managementAPI.users().get(id, null).execute());
	}
}
