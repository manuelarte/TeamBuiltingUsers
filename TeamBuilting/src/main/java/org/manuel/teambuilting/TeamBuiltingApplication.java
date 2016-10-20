package org.manuel.teambuilting;

import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.Date;

import org.manuel.teambuilting.dtos.PlayerDTO;
import org.manuel.teambuilting.dtos.PlayerToTeamDTO;
import org.manuel.teambuilting.dtos.TeamHistDTO;
import org.manuel.teambuilting.model.Player;
import org.manuel.teambuilting.model.PlayerId;
import org.manuel.teambuilting.model.TeamId;
import org.manuel.teambuilting.model.repository.PlayerRepository;
import org.manuel.teambuilting.model.repository.PlayerToTeamRepository;
import org.manuel.teambuilting.services.DTOSConverter;
import org.manuel.teambuilting.services.TeamHistService;
import org.manuel.teambuilting.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TeamBuiltingApplication implements CommandLineRunner {

	@Autowired
	private PlayerRepository playerRepository;

	@Autowired
	private TeamService teamService;

	@Autowired
	private TeamHistService teamHistService;

	@Autowired
	private PlayerToTeamRepository playerToTeamRepository;

	@Autowired
	private DTOSConverter dtosConverter;

	public static void main(final String[] args) {
		SpringApplication.run(TeamBuiltingApplication.class, args);
	}

	@Override
	public void run(final String... args) throws Exception {
		createData();
	}

	private void createData() {
		final PlayerDTO manu = createPlayer("Manuel Doncel Martos", "Úbeda, Jaén, 23400 Spain");
		final PlayerDTO pedro = createPlayer("Pedro Dans", "Coruña, Galicia, Spain");
		
		final Date startDevo2 = Date.from(LocalDate.of(1958, 1, 1).atStartOfDay(ZoneId.systemDefault()).toInstant());
		final TeamHistDTO devo2 = createTeam("Devo'58 2", startDevo2);
		
		// final Date startDevo2Fake = Date.from(LocalDate.of(1900, 1,
		// 1).atStartOfDay(ZoneId.systemDefault()).toInstant());
		// final TeamHistDTO previousDevo2 = teamHistService.saveTeamHist(new
		// TeamHistDTO.Builder(devo2).withId(null)
		// .withFromDate(startDevo2Fake)
		// .withToDate(startDevo2).build());
		
		final LocalDate september2015 = LocalDate.of(2015, Month.SEPTEMBER.getValue(), 1);
		playerToTeamRepository(manu.getId(), devo2.getTeamId(), september2015, null);
		playerToTeamRepository(pedro.getId(), devo2.getTeamId(), september2015.minusYears(1), null);

	}

	private TeamHistDTO createTeam(final String name, final Date fromDate) {
		final TeamHistDTO teamHist = new TeamHistDTO.Builder().withName(name).withFromDate(fromDate).build();
		return teamService.createTeam(teamHist);
	}

	private PlayerDTO createPlayer(final String name, final String bornAddress) {
		final PlayerDTO player = new PlayerDTO.Builder().withName(name).withBornAddress(bornAddress).build();
		final Player saved = playerRepository.save(dtosConverter.toPlayer().apply(player));
		return dtosConverter.toPlayerDTO().apply(saved);
	}

	private void playerToTeamRepository(final PlayerId playerId, final TeamId teamId, final LocalDate startDate,
			final LocalDate endDate) {

		final PlayerToTeamDTO playerToTeam = new PlayerToTeamDTO.Builder().withPlayerId(playerId).withTeamId(teamId)
				.withStartDate(startDate).withEndDate(endDate).build();

		playerToTeamRepository.save(dtosConverter.toPlayerToTeam().apply(playerToTeam));

	}
}
