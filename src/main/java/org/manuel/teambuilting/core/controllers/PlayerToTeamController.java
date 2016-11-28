package org.manuel.teambuilting.core.controllers;

import java.util.Collection;

import javax.inject.Inject;
import javax.validation.Valid;

import org.manuel.teambuilting.core.dtos.PlayerToTeamDTO;
import org.manuel.teambuilting.core.model.PlayerId;
import org.manuel.teambuilting.core.services.PlayerToTeamService;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/players/{playerId}/teams")
public class PlayerToTeamController {

	private final PlayerToTeamService playerToTeamService;

	@Inject
	public PlayerToTeamController(final PlayerToTeamService playerToTeamService) {
		this.playerToTeamService = playerToTeamService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public Collection<PlayerToTeamDTO> findPlayerHistory(@PathVariable("playerId") final PlayerId playerId) {
		Assert.notNull(playerId);
		return playerToTeamService.findPlayerHistory(playerId);
	}

	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	public PlayerToTeamDTO savePlayerToTeam(@PathVariable("playerId") final PlayerId playerId,
			@Valid @RequestBody final PlayerToTeamDTO playerToTeam) {
		Assert.notNull(playerToTeam);
		Assert.isTrue(playerToTeam.getPlayerId().getId().equals(playerId.getId()));
		return playerToTeamService.savePlayerToTeam(playerToTeam);
	}

}
