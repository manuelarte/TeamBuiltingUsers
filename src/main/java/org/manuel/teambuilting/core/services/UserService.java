package org.manuel.teambuilting.core.services;

import org.manuel.teambuilting.core.model.UserData;
import org.manuel.teambuilting.core.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.inject.Inject;
import java.util.HashSet;

/**
 * @author Manuel on 11/12/2016.
 */
@Service
public class UserService {

    private final UserRepository repository;

    @Inject
    public UserService(final UserRepository repository) {
        this.repository = repository;
    }

    public UserData getOrCreateUserData(final String userId) {
        final UserData retrieved = getUserData(userId);
        return retrieved != null ? retrieved : createUserData(userId, null, new HashSet<>());
    }

    private UserData getUserData(final String userId) {
        return repository.findByUserId(userId);
    }

    private UserData createUserData(final String userId, final String playerId, HashSet<String> adminOfTeams) {
        final UserData userData = new UserData(userId, playerId, adminOfTeams);
        return repository.save(userData);
    }


    public UserData update(final UserData userData) {
        Assert.notNull(userData.getId());
        return repository.save(userData);
    }
}
