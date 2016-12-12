package org.manuel.teambuilting.core.controllers;

import com.auth0.spring.security.api.Auth0JWTToken;
import org.manuel.teambuilting.core.config.Auth0Client;
import org.manuel.teambuilting.core.model.UserData;
import org.manuel.teambuilting.core.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

/**
 * @author Manuel Doncel Martos on 11/12/2016.
 */
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final Auth0Client auth0Client;

    @Inject
    public UserController(final UserService userService, final Auth0Client auth0Client) {
        this.userService = userService;
        this.auth0Client = auth0Client;
    }

    @RequestMapping(method = RequestMethod.GET)
    public UserData getUser() {
        final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        final String userId = auth0Client.getUser((Auth0JWTToken) auth).getId();
        return userService.getUserData(userId);
    }

}