package org.manuel.teambuilting.controllers;

import java.util.Set;

import org.manuel.teambuilting.dtos.PlayerDTO;
import org.manuel.teambuilting.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/players")
public class PlayerController {

	private final PlayerService playerService;

	@Autowired
	public PlayerController(final PlayerService playerService) {
		this.playerService = playerService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public Set<PlayerDTO> findPlayerByName(@RequestParam(value = "name", defaultValue = "") final String name) {
		Assert.notNull(name);
		return playerService.findPlayerByName(name);
	}

	@RequestMapping(method = RequestMethod.POST)
	public PlayerDTO savePlayer(@RequestBody final PlayerDTO player) {
		Assert.notNull(player);
		return playerService.savePlayer(player);
	}

}
