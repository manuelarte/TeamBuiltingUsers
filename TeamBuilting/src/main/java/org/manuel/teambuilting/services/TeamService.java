/**
 * 
 */
package org.manuel.teambuilting.services;

import org.manuel.teambuilting.dtos.Team;
import org.manuel.teambuilting.dtos.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Manuel Doncel Martos
 *
 */
@Service
public class TeamService {
	
	private final TeamRepository teamRepository;

	@Autowired
	public TeamService(final TeamRepository teamRepository) {
		this.teamRepository = teamRepository;
	}
	
	public Team saveTeam(final Team team) {
		teamRepository.save(team);
		return null;
	}

}
