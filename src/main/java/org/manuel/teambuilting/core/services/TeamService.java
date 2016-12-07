/**
 * 
 */
package org.manuel.teambuilting.core.services;

import javax.inject.Inject;

import org.manuel.teambuilting.core.dtos.TeamDTO;
import org.manuel.teambuilting.core.model.Team;
import org.manuel.teambuilting.core.model.repository.TeamHistRepository;
import org.manuel.teambuilting.core.model.repository.TeamRepository;
import org.springframework.security.access.prepost.PreAuthorize;
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

	@PreAuthorize("hasAuthority('ROLE_USER')")
	public TeamDTO createTeam(final TeamDTO team) {
		Assert.notNull(team);
		final Team savedTeam = teamRepository.save(dtosConverter.createTeam(team));
		return dtosConverter.createTeamDTO(savedTeam);

	}

}