package org.manuel.teambuilting.core.controllers.query;

import java.util.Optional;
import java.util.Set;

import javax.inject.Inject;

import org.manuel.teambuilting.core.exceptions.ErrorCode;
import org.manuel.teambuilting.core.exceptions.ValidationRuntimeException;
import org.manuel.teambuilting.core.model.Player;
import org.manuel.teambuilting.core.model.PlayerToTeamSportDetails;
import org.manuel.teambuilting.core.services.PlayerToTeamSportDetailsService;
import org.manuel.teambuilting.core.services.query.PlayerQueryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/core/players")
public class PlayerQueryController extends AbstractQueryController<Player, String, PlayerQueryService> {

	private final PlayerToTeamSportDetailsService playerToTeamSportDetailsService;

	@Inject
	public PlayerQueryController(final PlayerQueryService playerQueryService,
			final PlayerToTeamSportDetailsService playerToTeamSportDetailsService) {
		super(playerQueryService);
		this.playerToTeamSportDetailsService = playerToTeamSportDetailsService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public Page<Player> findPlayerByName(@PageableDefault(page = 0, size = 20) final Pageable pageable,
		@RequestParam(value = "name", defaultValue = "") final String name) {
		Assert.notNull(name);
		return queryService.findPlayerByName(pageable, name);
	}

	@RequestMapping(path = "/{playerId}/details", method = RequestMethod.GET)
	public Set<PlayerToTeamSportDetails> findPlayerDetails(@PathVariable("playerId") final String playerId) {
		Assert.hasLength(playerId);
		return playerToTeamSportDetailsService.findPlayerDetails(playerId);
	}

	@RequestMapping(path = "/{playerId}/details/{sport}", method = RequestMethod.GET)
	public ResponseEntity<PlayerToTeamSportDetails> findPlayerDetailsForSport(@PathVariable("playerId") final String playerId, @PathVariable("sport") final String sport) {
		Assert.hasLength(playerId);
		Assert.hasLength(sport);
		final Optional<PlayerToTeamSportDetails> playerToTeamSportDetails = playerToTeamSportDetailsService.findPlayerDetailsForSport(playerId, sport);
		if (playerToTeamSportDetails.isPresent()) {
			return ResponseEntity.ok(playerToTeamSportDetails.get());
		}
		throw new ValidationRuntimeException(ErrorCode.PLAYER_DETAIL_FOR_SPORT_NOT_FOUND, playerId, sport);
	}

}
