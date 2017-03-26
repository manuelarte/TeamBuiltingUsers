package org.manuel.teambuilting.core.controllers.command;

import lombok.AllArgsConstructor;
import org.manuel.teambuilting.core.model.Player;
import org.manuel.teambuilting.core.model.PlayerToTeamSportDetails;
import org.manuel.teambuilting.core.services.PlayerToTeamSportDetailsService;
import org.manuel.teambuilting.core.services.command.PlayerCommandService;
import org.manuel.teambuilting.core.services.geocoding.PlayerGeocodingService;
import org.manuel.teambuilting.core.services.query.PlayerQueryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/core/players")
@AllArgsConstructor
public class PlayerCommandController {

	private final PlayerCommandService playerCommandService;
	private final PlayerGeocodingService playerGeocodingService;
	private final PlayerQueryService playerQueryService;
	private final PlayerToTeamSportDetailsService playerToTeamSportDetailsService;

	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	public Player savePlayer(@Valid @RequestBody final Player player) {
		final Player saved = playerCommandService.save(player);
		if (Optional.ofNullable(saved.getBornAddress()).isPresent()) {
			playerGeocodingService.asyncReq(saved);
		}
		return saved;
	}

	@RequestMapping(path = "/{playerId}", method = RequestMethod.DELETE)
	public ResponseEntity<Player> deletePlayer(@PathVariable("playerId") final String playerId) {
		final Optional<Player> player = playerQueryService.findOne(playerId);
		if (!player.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		playerCommandService.delete(playerId);
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
