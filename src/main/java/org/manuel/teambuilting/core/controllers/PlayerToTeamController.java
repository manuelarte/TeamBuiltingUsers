package org.manuel.teambuilting.core.controllers;

import org.manuel.teambuilting.core.model.PlayerId;
import org.manuel.teambuilting.core.model.PlayerToTeam;
import org.manuel.teambuilting.core.model.PlayerToTeamId;
import org.manuel.teambuilting.core.services.PlayerToTeamService;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("/players/{playerId}/teams")
public class PlayerToTeamController {

	private final PlayerToTeamService playerToTeamService;

	@Inject
	public PlayerToTeamController(final PlayerToTeamService playerToTeamService) {
		this.playerToTeamService = playerToTeamService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public Collection<PlayerToTeam> findPlayerHistory(@PathVariable("playerId") final PlayerId playerId) {
		Assert.notNull(playerId);
		return playerToTeamService.findPlayerHistory(playerId);
	}

	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	public PlayerToTeam savePlayerToTeam(@PathVariable("playerId") final PlayerId playerId,
			@Valid @RequestBody final PlayerToTeam playerToTeam) {
		Assert.notNull(playerToTeam);
		Assert.isTrue(playerToTeam.getPlayerId().equals(playerId.getId()));
		return playerToTeamService.savePlayerToTeam(playerToTeam);
	}

	@RequestMapping(value = "/{playerToTeamId}", method = RequestMethod.DELETE, produces = "application/json")
	public void deletePlayerToTeam(@PathVariable("playerId") final PlayerId playerId,
										   @PathVariable("playerToTeamId") final PlayerToTeamId playerToTeamId) {
		playerToTeamService.deletePlayerToTeam(playerToTeamId);
	}

}
