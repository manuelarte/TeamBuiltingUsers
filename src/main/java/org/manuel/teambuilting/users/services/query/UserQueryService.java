package org.manuel.teambuilting.users.services.query;

import com.auth0.json.mgmt.users.User;

import java.util.Optional;


/**
 * @author manuel.doncel.martos
 * @since 29-3-2017
 */
public interface UserQueryService {

	Optional<User> findById(String id);
}
