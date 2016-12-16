package org.manuel.teambuilting.core.controllers;

import java.util.Set;

import javax.inject.Inject;
import javax.validation.Valid;

import org.manuel.teambuilting.core.config.Auth0Client;
import org.manuel.teambuilting.core.model.Player;
import org.manuel.teambuilting.core.model.PlayerId;
import org.manuel.teambuilting.core.model.PlayerToTeamSportDetails;
import org.manuel.teambuilting.core.services.PlayerCommandService;
import org.manuel.teambuilting.core.services.PlayerQueryService;
import org.manuel.teambuilting.core.services.PlayerToTeamSportDetailsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

	private final Auth0Client auth0Client;
	private final PlayerCommandService playerCommandService;
	private final PlayerQueryService playerQueryService;
	private final PlayerToTeamSportDetailsService playerToTeamSportDetailsService;

	@Inject
	public PlayerController(final Auth0Client auth0Client, final PlayerCommandService playerCommandService, final PlayerQueryService playerQueryService,
			final PlayerToTeamSportDetailsService playerToTeamSportDetailsService) {
		this.auth0Client = auth0Client;
		this.playerCommandService = playerCommandService;
		this.playerQueryService = playerQueryService;
		this.playerToTeamSportDetailsService = playerToTeamSportDetailsService;
	}

	@RequestMapping(path = "/{playerId}", method = RequestMethod.GET)
	public Player getPlayer(@PathVariable("playerId") final PlayerId playerId) {
		Assert.notNull(playerId);
		return playerQueryService.getPlayer(playerId);
	}

	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	public Player savePlayer(@Valid @RequestBody final Player player) {
		return playerCommandService.savePlayer(player);
	}

	@RequestMapping(path = "/{playerId}", method = RequestMethod.DELETE)
	public ResponseEntity<Player> deleteUser(@PathVariable("playerId") final String playerId) {
		Player player = playerQueryService.getPlayer(new PlayerId(playerId));
		if (player == null) {
			return new ResponseEntity<Player>(HttpStatus.NOT_FOUND);
		}

		playerCommandService.deletePlayer(new PlayerId(playerId));
		return new ResponseEntity<Player>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(method = RequestMethod.GET)
	public Set<Player> findPlayerByName(@RequestParam(value = "name", defaultValue = "") final String name) {
		Assert.notNull(name);
		return playerQueryService.findPlayerByName(name);
	}

	@RequestMapping(path = "/{playerId}/details", method = RequestMethod.GET)
	public PlayerToTeamSportDetails findPlayerDetails(@PathVariable("playerId") final PlayerId playerId,
			@RequestParam(value = "sport", defaultValue = "Football") final String sport) {
		Assert.notNull(playerId);
		Assert.notNull(sport);
		return playerToTeamSportDetailsService.findPlayerDetails(playerId, sport);
	}

	@RequestMapping(path = "/{playerId}/details", method = RequestMethod.POST)
	public PlayerToTeamSportDetails savePlayerDetails(@PathVariable("playerId") final PlayerId playerId,
			@Valid @RequestBody final PlayerToTeamSportDetails playerToTeamSportDetails) {
		Assert.notNull(playerId);
		Assert.isTrue(playerId.getId().equals(playerToTeamSportDetails.getPlayerId()));
		return playerToTeamSportDetailsService.savePlayerDetails(playerToTeamSportDetails);
	}

}
