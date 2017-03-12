package org.manuel.teambuilting.core.controllers.command;

import java.util.Optional;

import javax.inject.Inject;
import javax.validation.Valid;

import org.manuel.teambuilting.core.config.Auth0Client;
import org.manuel.teambuilting.core.model.Player;
import org.manuel.teambuilting.core.model.PlayerToTeamSportDetails;
import org.manuel.teambuilting.core.services.command.PlayerCommandService;
import org.manuel.teambuilting.core.services.query.PlayerQueryService;
import org.manuel.teambuilting.core.services.PlayerToTeamSportDetailsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/core/players")
public class PlayerCommandController {

	private final Auth0Client auth0Client;
	private final PlayerCommandService playerCommandService;
	private final PlayerQueryService playerQueryService;
	private final PlayerToTeamSportDetailsService playerToTeamSportDetailsService;

	@Inject
	public PlayerCommandController(final Auth0Client auth0Client, final PlayerCommandService playerCommandService, final PlayerQueryService playerQueryService,
			final PlayerToTeamSportDetailsService playerToTeamSportDetailsService) {
		this.auth0Client = auth0Client;
		this.playerCommandService = playerCommandService;
		this.playerQueryService = playerQueryService;
		this.playerToTeamSportDetailsService = playerToTeamSportDetailsService;
	}

	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	public Player savePlayer(@Valid @RequestBody final Player player) {
		return playerCommandService.savePlayer(player);
	}

	@RequestMapping(path = "/{playerId}", method = RequestMethod.DELETE)
	public ResponseEntity<Player> deletePlayer(@PathVariable("playerId") final String playerId) {
		final Optional<Player> player = playerQueryService.findOne(playerId);
		if (!player.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		playerCommandService.deletePlayer(playerId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(path = "/{playerId}/details", method = RequestMethod.POST)
	public PlayerToTeamSportDetails savePlayerDetails(@PathVariable("playerId") final String playerId,
			@Valid @RequestBody final PlayerToTeamSportDetails playerToTeamSportDetails) {
		Assert.hasLength(playerId);
		Assert.isTrue(playerId.equals(playerToTeamSportDetails.getPlayerId()));
		return playerToTeamSportDetailsService.savePlayerDetails(playerToTeamSportDetails);
	}

}
