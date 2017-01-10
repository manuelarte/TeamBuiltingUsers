/**
 * 
 */
package org.manuel.teambuilting.core.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.manuel.teambuilting.core.model.TeamSport;
import org.manuel.teambuilting.core.model.TeamSportPosition;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Manuel Doncel Martos
 *
 */
@RestController
@RequestMapping("/core/sports")
public class SportController {

	public SportController() {
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<TeamSport> getSportsAvailable() {
		return Arrays.asList(TeamSport.class.getEnumConstants());
	}

	@RequestMapping(path = "/{teamSportname}", method = RequestMethod.GET)
	public List<TeamSportPosition> getTeamSport(@PathVariable("teamSportname") @NotNull final String teamSportName) {
		final Optional<TeamSport> sport = Arrays.stream(TeamSport.values())
				.filter(teamSport -> teamSport.getName().equals(teamSportName)).findFirst();
		if (sport.isPresent()) {
			return Arrays.asList(sport.get().getSportPositions());
		} else {
			throw new IllegalArgumentException();
		}
	}

}
