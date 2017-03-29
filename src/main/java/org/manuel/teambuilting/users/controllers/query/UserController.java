package org.manuel.teambuilting.users.controllers.query;

import org.manuel.teambuilting.users.model.User;
import org.manuel.teambuilting.users.services.query.UserQueryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

/**
 * @author manuel.doncel.martos
 * @since 29-3-2017
 */
@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

	private final UserQueryService queryService;

	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<User> findById(@PathVariable("id") final String id) {
		return ResponseEntity.ok(queryService.findById(id).get());
	}
}
