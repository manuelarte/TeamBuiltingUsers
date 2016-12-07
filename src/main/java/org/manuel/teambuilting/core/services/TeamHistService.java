/**
 * 
 */
package org.manuel.teambuilting.core.services;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.manuel.teambuilting.core.dtos.TeamHistDTO;
import org.manuel.teambuilting.core.model.Team;
import org.manuel.teambuilting.core.model.TeamHist;
import org.manuel.teambuilting.core.model.TeamHistId;
import org.manuel.teambuilting.core.model.TeamId;
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
public class TeamHistService {

	private final TeamRepository teamRepository;
	private final TeamHistRepository teamHistRepository;
	private final DTOSConverter dtosConverter;
	
	@Inject
	public TeamHistService(final TeamRepository teamRepository, final TeamHistRepository teamHistRepository,
			final DTOSConverter dtosConverter) {
		this.teamRepository = teamRepository;
		this.teamHistRepository = teamHistRepository;
		this.dtosConverter = dtosConverter;
	}

	public Set<TeamHistDTO> findTeamBy(final String sport, final String name) {
		final Set<Team> teamsForSport = teamRepository.findBySportLikeIgnoreCase(sport);
		final Set<TeamHist> matchingName = teamHistRepository.findByNameLikeIgnoreCase(name);
		return matchingName.stream()
				.filter(th -> teamsForSport.stream().map(team -> team.getId())
						.collect(Collectors.toSet()).contains(th.getTeamId()))
				.map(th -> dtosConverter.createTeamHistDTO(th, getTeamFrom(th))).collect(Collectors.toSet());
	}

	@PreAuthorize("hasAuthority('ROLE_USER')")
	public TeamHistDTO saveTeamHist(final TeamHistDTO teamHist) {
		Assert.notNull(teamHist);
		final Team team = teamRepository.findOne(teamHist.getTeamId().getId());
		final TeamHist saved = teamHistRepository.save(dtosConverter.createTeamHist(teamHist));
		return new TeamHistDTO(new TeamHistId(saved.getId()), teamHist.getTeamId(), teamHist.getName(),
				team.getSport(), teamHist.getLocation(), teamHist.getFromDate(),
				teamHist.getToDate());
	}

	public TeamHistDTO getLastTeamHist(final TeamId teamId) {
		Assert.notNull(teamId);
		final Team team = teamRepository.findOne(teamId.getId());
		final List<TeamHist> findByTeamId = teamHistRepository.findByTeamIdOrderByFromDateDesc(teamId.getId());
		final TeamHist teamHist = findByTeamId.get(0);
		return dtosConverter.createTeamHistDTO(teamHist, team);
	}

	public TeamHistDTO getTeamHist(final TeamHistId teamHistId) {
		final TeamHist teamHist = teamHistRepository.findOne(teamHistId.getId());
		final Team team = teamRepository.findOne(teamHist.getTeamId());
		return dtosConverter.createTeamHistDTO(teamHist, team);
	}

	private Team getTeamFrom(final TeamHist teamHist) {
		return teamRepository.findOne(teamHist.getTeamId());
	}

}
