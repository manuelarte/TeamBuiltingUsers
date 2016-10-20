/**
 * 
 */
package org.manuel.teambuilting.services;

import java.util.List;

import org.manuel.teambuilting.dtos.TeamHistDTO;
import org.manuel.teambuilting.model.TeamHist;
import org.manuel.teambuilting.model.TeamHistId;
import org.manuel.teambuilting.model.TeamId;
import org.manuel.teambuilting.model.repository.TeamHistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * @author Manuel Doncel Martos
 *
 */
@Service
public class TeamHistService {

	private final TeamHistRepository teamHistRepository;
	private final DTOSConverter dtosConverter;
	
	@Autowired
	public TeamHistService(final TeamHistRepository teamHistRepository, final DTOSConverter dtosConverter) {
		this.teamHistRepository = teamHistRepository;
		this.dtosConverter = dtosConverter;
	}

	public TeamHistDTO saveTeamHist(final TeamHistDTO teamHist) {
		Assert.notNull(teamHist);
		final TeamHist saved = teamHistRepository.save(dtosConverter.createTeamHist(teamHist));
		return new TeamHistDTO.Builder(teamHist).withId(new TeamHistId(saved.getId())).build();
	}

	public TeamHistDTO getLastTeamHist(final TeamId teamId) {
		Assert.notNull(teamId);
		final List<TeamHist> findByTeamId = teamHistRepository.findByTeamIdOrderByFromDateDesc(teamId.getId());
		final TeamHist teamHist = findByTeamId.get(0);
		return dtosConverter.createTeamHistDTO(teamHist);
	}

	public TeamHistDTO getTeamHist(final TeamHistId teamHistId) {
		final TeamHist teamHist = teamHistRepository.findOne(teamHistId.getId());
		return dtosConverter.createTeamHistDTO(teamHist);
	}

}
