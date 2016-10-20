/**
 * 
 */
package org.manuel.teambuilting.services;

import org.manuel.teambuilting.dtos.TeamDTO;
import org.manuel.teambuilting.model.Team;
import org.manuel.teambuilting.model.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Manuel Doncel Martos
 *
 */
@Service
public class TeamService {
	
	private final TeamRepository teamRepository;
	private final DTOSConverter dtosConverter;

	@Autowired
	public TeamService(final TeamRepository teamRepository, final DTOSConverter dtosConverter) {
		this.teamRepository = teamRepository;
		this.dtosConverter = dtosConverter;
	}
	
	public TeamDTO saveTeam(final TeamDTO team) {
		final Team created = teamRepository.save(dtosConverter.convertTeamDTO().apply(team));
		return dtosConverter.convertTeam().apply(created);
	}

}
