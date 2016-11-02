/**
 * 
 */
package org.manuel.teambuilting.services;

import javax.inject.Inject;

import org.manuel.teambuilting.dtos.TeamHistDTO;
import org.manuel.teambuilting.model.Team;
import org.manuel.teambuilting.model.TeamId;
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
	private final TeamHistService teamHistService;

	@Inject
	public TeamService(final TeamRepository teamRepository, final TeamHistService teamHistService,
			final TeamHistRepository teamHistRepository,
			final DTOSConverter dtosConverter) {
		this.teamRepository = teamRepository;
		this.teamHistService = teamHistService;
	}
	
	public TeamHistDTO createTeam(final TeamHistDTO teamHist) {
		Assert.notNull(teamHist);
		final Team team = teamRepository.save(new Team());
		final TeamHistDTO updatedTeamHist = updateTeamHist(new TeamId(team.getId()), teamHist);
		return teamHistService.saveTeamHist(updatedTeamHist);

	}

	private TeamHistDTO updateTeamHist(final TeamId teamId, final TeamHistDTO teamHist) {
		return new TeamHistDTO.Builder(teamHist).withTeamId(teamId).build();
	}

}
