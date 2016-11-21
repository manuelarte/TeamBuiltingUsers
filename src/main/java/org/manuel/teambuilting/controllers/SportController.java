/**
 * 
 */
package org.manuel.teambuilting.controllers;

import java.util.Arrays;
import java.util.List;

import org.manuel.teambuilting.model.TeamSport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Manuel Doncel Martos
 *
 */
@RestController
@RequestMapping("/sports")
public class SportController {

	public SportController() {
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<TeamSport> getSportsAvailable() {
		return Arrays.asList(TeamSport.class.getEnumConstants());
	}

}
