package org.manuel.teambuilting.services;

import org.manuel.teambuilting.dtos.PlayerDTO;
import org.manuel.teambuilting.model.Player;
import org.manuel.teambuilting.model.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {

	private final PlayerRepository playerRepository;
	private final DTOSConverter dtosConverter;

	@Autowired
	public PlayerService(final PlayerRepository playerRepository, final DTOSConverter dtosConverter) {
		this.playerRepository = playerRepository;
		this.dtosConverter = dtosConverter;
	}

	public PlayerDTO savePlayer(final PlayerDTO player) {
		final Player created = playerRepository.save(dtosConverter.toPlayer().apply(player));
		return dtosConverter.toPlayerDTO().apply(created);
	}

}
