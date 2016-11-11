package org.manuel.teambuilting.controllers;

import java.util.Collection;
import java.util.Set;

import javax.inject.Inject;

import org.manuel.teambuilting.dtos.PlayerDTO;
import org.manuel.teambuilting.dtos.PlayerToTeamDTO;
import org.manuel.teambuilting.dtos.PlayerToTeamSportDetailsDTO;
import org.manuel.teambuilting.model.PlayerId;
import org.manuel.teambuilting.services.PlayerService;
import org.manuel.teambuilting.services.PlayerToTeamSportDetailsService;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/players")
public class PlayerController {

	private final PlayerService playerService;
	private final PlayerToTeamSportDetailsService playerToTeamSportDetailsService;

	@Inject
	public PlayerController(final PlayerService playerService,
			final PlayerToTeamSportDetailsService playerToTeamSportDetailsService) {
		this.playerService = playerService;
		this.playerToTeamSportDetailsService = playerToTeamSportDetailsService;
	}


	@RequestMapping(path = "/{playerId}", method = RequestMethod.GET)
	public PlayerDTO getPlayer(@PathVariable("playerId") final PlayerId playerId) {
		Assert.notNull(playerId);
		return playerService.getPlayer(playerId);
	}

	@RequestMapping(method = RequestMethod.POST)
	public PlayerDTO savePlayer(@RequestBody final PlayerDTO player) {
		Assert.notNull(player);
		return playerService.savePlayer(player);
	}

	@RequestMapping(method = RequestMethod.GET)
	public Set<PlayerDTO> findPlayerByName(@RequestParam(value = "name", defaultValue = "") final String name) {
		Assert.notNull(name);
		return playerService.findPlayerByName(name);
	}

	@RequestMapping(path = "/{playerId}/details", method = RequestMethod.GET)
	public PlayerToTeamSportDetailsDTO findPlayerDetails(@PathVariable("playerId") final PlayerId playerId,
			@RequestParam(value = "sport", defaultValue = "Football") final String sport) {
		Assert.notNull(playerId);
		Assert.notNull(sport);
		return playerToTeamSportDetailsService.findPlayerDetails(playerId, sport);
	}

	@RequestMapping(path = "/{playerId}/history", method = RequestMethod.GET)
	public Collection<PlayerToTeamDTO> findPlayerHistory(@PathVariable("playerId") final PlayerId playerId) {
		Assert.notNull(playerId);
		return playerService.findPlayerHistory(playerId);
	}

}