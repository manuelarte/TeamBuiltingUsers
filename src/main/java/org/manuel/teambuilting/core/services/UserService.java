package org.manuel.teambuilting.core.services;

import org.manuel.teambuilting.core.model.UserData;
import org.manuel.teambuilting.core.model.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

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

    public UserData getUserData(final String userId) {
        return repository.findByUserId(userId);
    }

}
