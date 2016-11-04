/**
 * 
 */
package org.manuel.teambuilting.services;

import javax.inject.Inject;

import org.manuel.teambuilting.dtos.TeamDTO;
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
	private final DTOSConverter dtosConverter;

	@Inject
	public TeamService(final TeamRepository teamRepository, final TeamHistService teamHistService,
			final TeamHistRepository teamHistRepository,
			final DTOSConverter dtosConverter) {
		this.teamRepository = teamRepository;
		this.teamHistService = teamHistService;
		this.dtosConverter = dtosConverter;
	}
	
	public TeamHistDTO createTeam(final TeamDTO team, final TeamHistDTO teamHist) {
		Assert.notNull(teamHist);
		final Team savedTeam = teamRepository.save(dtosConverter.createTeam(team));
		final TeamHistDTO updatedTeamHist = updateTeamHist(new TeamId(savedTeam.getId()), teamHist);
		return teamHistService.saveTeamHist(updatedTeamHist);

	}

	private TeamHistDTO updateTeamHist(final TeamId teamId, final TeamHistDTO teamHist) {
		return new TeamHistDTO(teamHist.getId(), teamId, teamHist.getName(), teamHist.getLocation(),
				teamHist.getEmblemPath(), teamHist.getFromDate(), teamHist.getToDate());
	}

}
