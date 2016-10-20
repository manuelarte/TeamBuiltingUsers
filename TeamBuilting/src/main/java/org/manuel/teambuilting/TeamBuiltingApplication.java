package org.manuel.teambuilting;

import java.time.LocalDate;
import java.time.Month;

import org.manuel.teambuilting.dtos.PlayerDTO;
import org.manuel.teambuilting.dtos.PlayerToTeamDTO;
import org.manuel.teambuilting.dtos.TeamDTO;
import org.manuel.teambuilting.model.Player;
import org.manuel.teambuilting.model.Team;
import org.manuel.teambuilting.model.repository.PlayerRepository;
import org.manuel.teambuilting.model.repository.PlayerToTeamRepository;
import org.manuel.teambuilting.model.repository.TeamRepository;
import org.manuel.teambuilting.services.DTOSConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TeamBuiltingApplication implements CommandLineRunner {

	@Autowired
	private PlayerRepository playerRepository;

	@Autowired
	private TeamRepository teamRepository;

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

		final TeamDTO devo2 = createTeam("Devo'58 2");
		
		final LocalDate september2015 = LocalDate.of(2015, Month.SEPTEMBER.getValue(), 1);
		playerToTeamRepository(manu, devo2, september2015, null);
		playerToTeamRepository(pedro, devo2, september2015.minusYears(1), null);

	}

	private TeamDTO createTeam(final String name) {
		final TeamDTO team = new TeamDTO.Builder().withName(name).build();
		final Team saved = teamRepository.save(dtosConverter.convertTeamDTO().apply(team));
		return dtosConverter.convertTeam().apply(saved);
	}

	private PlayerDTO createPlayer(final String name, final String bornAddress) {
		final PlayerDTO player = new PlayerDTO.Builder().withName(name).withBornAddress(bornAddress).build();
		final Player saved = playerRepository.save(dtosConverter.toPlayer().apply(player));
		return dtosConverter.toPlayerDTO().apply(saved);
	}

	private void playerToTeamRepository(final PlayerDTO player, final TeamDTO team, final LocalDate startDate,
			final LocalDate endDate) {

		final PlayerToTeamDTO playerToTeam = new PlayerToTeamDTO.Builder().withPlayerId(player.getId())
				.withTeamId(team.getId()).withStartDate(startDate).withEndDate(endDate).build();

		playerToTeamRepository.save(dtosConverter.toPlayerToTeam().apply(playerToTeam));

	}
}
