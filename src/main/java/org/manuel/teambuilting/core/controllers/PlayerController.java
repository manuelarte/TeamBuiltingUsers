package org.manuel.teambuilting.core.controllers;

import java.util.Optional;
import java.util.Set;

import javax.inject.Inject;
import javax.validation.Valid;

import org.manuel.teambuilting.core.config.Auth0Client;
import org.manuel.teambuilting.core.exceptions.ValidationRuntimeException;
import org.manuel.teambuilting.core.model.Player;
import org.manuel.teambuilting.core.model.PlayerToTeamSportDetails;
import org.manuel.teambuilting.core.services.PlayerCommandService;
import org.manuel.teambuilting.core.services.PlayerQueryService;
import org.manuel.teambuilting.core.services.PlayerToTeamSportDetailsService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
@RequestMapping("/core/players")
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
	public ResponseEntity<Player> getPlayer(@PathVariable("playerId") final String playerId) {
		Assert.notNull(playerId);
		final Optional<Player> player = playerQueryService.getPlayer(playerId);
		if (player.isPresent()) {
			return ResponseEntity.ok(player.get());
		}
		throw new ValidationRuntimeException("0001", "player with id " + playerId + " not found", "player with id " + playerId + " not found");
	}

	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	public Player savePlayer(@Valid @RequestBody final Player player) {
		return playerCommandService.savePlayer(player);
	}

	@RequestMapping(path = "/{playerId}", method = RequestMethod.DELETE)
	public ResponseEntity<Player> deleteUser(@PathVariable("playerId") final String playerId) {
		final Optional<Player> player = playerQueryService.getPlayer(playerId);
		if (!player.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		playerCommandService.deletePlayer(playerId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(method = RequestMethod.GET)
	public Page<Player> findPlayerByName(@PageableDefault(page = 0, size = 20) final Pageable pageable,
		@RequestParam(value = "name", defaultValue = "") final String name) {
		Assert.notNull(name);
		return playerQueryService.findPlayerByName(pageable, name);
	}

	@RequestMapping(path = "/{playerId}/details", method = RequestMethod.GET)
	public Set<PlayerToTeamSportDetails> findPlayerDetails(@PathVariable("playerId") final String playerId) {
		Assert.hasLength(playerId);
		return playerToTeamSportDetailsService.findPlayerDetails(playerId);
	}

	@RequestMapping(path = "/{playerId}/details/{sport}", method = RequestMethod.GET)
	public PlayerToTeamSportDetails findPlayerDetailsForSport(@PathVariable("playerId") final String playerId, @PathVariable("sport") final String sport) {
		Assert.notNull(playerId);
		Assert.notNull(sport);
		return playerToTeamSportDetailsService.findPlayerDetailsForSport(playerId, sport);
	}

	@RequestMapping(path = "/{playerId}/details", method = RequestMethod.POST)
	public PlayerToTeamSportDetails savePlayerDetails(@PathVariable("playerId") final String playerId,
			@Valid @RequestBody final PlayerToTeamSportDetails playerToTeamSportDetails) {
		Assert.hasLength(playerId);
		Assert.isTrue(playerId.equals(playerToTeamSportDetails.getPlayerId()));
		return playerToTeamSportDetailsService.savePlayerDetails(playerToTeamSportDetails);
	}

}
