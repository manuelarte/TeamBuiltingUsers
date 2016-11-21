/**
 * 
 */
package org.manuel.teambuilting.services;

import javax.inject.Inject;

import org.manuel.teambuilting.dtos.TeamDTO;
import org.manuel.teambuilting.model.Team;
import org.manuel.teambuilting.model.repository.TeamHistRepository;
import org.manuel.teambuilting.model.repository.TeamRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * @author Manuel Doncel Martos
 *
 */
@Service
public class TeamService {
	
	private final TeamRepository teamRepository;
	private final DTOSConverter dtosConverter;

	@Inject
	public TeamService(final TeamRepository teamRepository, final TeamHistService teamHistService,
			final TeamHistRepository teamHistRepository,
			final DTOSConverter dtosConverter) {
		this.teamRepository = teamRepository;
		this.dtosConverter = dtosConverter;
	}
	
	public TeamDTO createTeam(final TeamDTO team) {
		Assert.notNull(team);
		final Team savedTeam = teamRepository.save(dtosConverter.createTeam(team));
		return dtosConverter.createTeamDTO(savedTeam);

	}

}
