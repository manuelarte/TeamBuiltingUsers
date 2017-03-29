package org.manuel.teambuilting.users.services.query;

import java.util.Optional;

import org.manuel.teambuilting.users.model.User;

/**
 * @author manuel.doncel.martos
 * @since 29-3-2017
 */
public interface UserQueryService {

	Optional<User> findById(String id);
}
