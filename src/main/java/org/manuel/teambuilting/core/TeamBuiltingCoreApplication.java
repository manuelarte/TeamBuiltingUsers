package org.manuel.teambuilting.core;

import static org.manuel.teambuilting.core.model.sports.FootballPosition.CAM;
import static org.manuel.teambuilting.core.model.sports.FootballPosition.LB;
import static org.manuel.teambuilting.core.model.sports.FootballPosition.LM;
import static org.manuel.teambuilting.core.model.sports.FootballPosition.LW;

import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.manuel.teambuilting.core.model.Player;
import org.manuel.teambuilting.core.model.PlayerToTeam;
import org.manuel.teambuilting.core.model.PlayerToTeamSportDetails;
import org.manuel.teambuilting.core.model.Team;
import org.manuel.teambuilting.core.model.TeamSport;
import org.manuel.teambuilting.core.model.TeamSportPosition;
import org.manuel.teambuilting.core.repositories.PlayerRepository;
import org.manuel.teambuilting.core.repositories.PlayerToTeamRepository;
import org.manuel.teambuilting.core.repositories.PlayerToTeamSportDetailsRepository;
import org.manuel.teambuilting.core.services.command.TeamCommandService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class TeamBuiltingCoreApplication {

	@Inject
	private PlayerRepository playerRepository;

	@Inject
	private TeamCommandService teamCommandService;

	@Inject
	private PlayerToTeamRepository playerToTeamRepository;

	@Inject
	private PlayerToTeamSportDetailsRepository playerToTeamSportDetailsRepository;

	public static void main(final String[] args) {
		SpringApplication.run(TeamBuiltingCoreApplication.class, args);
	}

	//@Override
	public void run(final String... args) throws Exception {
		// createData();
	}

	private void createData() {
		final Player javiLeon = createPlayer("Javier leon", "Javi", Optional.of('M'),
				"Soria, Castille and Leon, Spain");
		final Player sanne = createPlayer("Sanne", "Sanne", Optional.of('M'), "Amsterdam, Netherlands");
		final Player mihaiDolghan = createPlayer("Mihai Dolghan", "Mihai", Optional.of('M'), "Bucharest, Romania");
		final Player oscar = createPlayer("Oscar", "Oscar", Optional.of('M'), "Santander, Cantabria, Spain");
		final Player borja = createPlayer("Borja Sacristan", "Borja", Optional.of('M'), "Madrid, Madrid, Spain");
		final Player manu = createPlayer("Manuel Doncel Martos", "Manu D", Optional.of('M'),
				"Ubeda, Jaen, 23400 Spain");
		addPlayerDetails(manu.getId(), "Player with offensive vocation", LB,
				new HashSet<TeamSportPosition>(Arrays.asList(LM, CAM, LW)));

		final Player pedro = createPlayer("Pedro Dans", "Pedro", Optional.of('M'), "Coruna, Galicia, Spain");
		final Player dennis = createPlayer("Dennis Bakker", "Dennis", Optional.of('M'), "Madrid, Madrid, Spain");
		final Player karim = createPlayer("Karim", "Karim", Optional.of('M'), "Guadalajara, Madrid, Spain");
		final Player diego = createPlayer("Diego Ramonde", "Diego", Optional.of('M'), "Coruna, Galicia, Spain");
		final Player nelson = createPlayer("Nelson Alfonso", "Nelson", Optional.of('M'), "Lisbon, Portugal");
		final Player theo = createPlayer("Theodor Phantender", "Theo D'Or", Optional.of('M'),
				"Amsterdam, Netherlands");
		final Player daniel = createPlayer("Daniel Dittmar", "Daniel", Optional.of('M'), "Sydney, Australia");
		final Player daan = createPlayer("Dann Farjon", "Daan", Optional.of('M'), "Amsterdam, Netherlands");
		final Player tomasVirkick = createPlayer("Tomas Virkick", "Virco", Optional.of('M'), "Slovakia");
		final Player tomasZ = createPlayer("Tomas Z", "Tomas", Optional.of('M'), "Slovakia");
		final Player kuba = createPlayer("Kuba", "Kuba", Optional.of('M'), "Krakow, Poland");
		
		final Date startDevo2 = toDate(LocalDate.of(1958, 1, 1));
		final String devoAddress = "Herman Bonpad 4, 1067 SN Amsterdam";
		final Team devo2 = createTeam(TeamSport.FOOTBALL, "Devo'58 Zaterdag 2", devoAddress, startDevo2,
				Optional.empty());
		
		// final Date startDevo2Fake = Date.from(LocalDate.of(1900, 1,
		// 1).atStartOfDay(ZoneId.systemDefault()).toInstant());
		// final TeamHistDTO previousDevo2 = teamHistService.saveTeamHist(new
		// TeamHistDTO.Builder(devo2).withId(null)
		// .withFromDate(startDevo2Fake)
		// .withToDate(startDevo2).build());
		
		final LocalDate september2015 = LocalDate.of(2015, Month.SEPTEMBER.getValue(), 1);
		playerToTeamRepository(manu.getId(), devo2.getId(), toDate(september2015), null);
		playerToTeamRepository(javiLeon.getId(), devo2.getId(), toDate(september2015), null);
		playerToTeamRepository(diego.getId(), devo2.getId(), toDate(september2015), null);
		playerToTeamRepository(nelson.getId(), devo2.getId(), toDate(september2015), null);
		playerToTeamRepository(daniel.getId(), devo2.getId(), toDate(september2015), null);

		playerToTeamRepository(sanne.getId(), devo2.getId(), toDate(september2015.minusYears(1)), null);
		playerToTeamRepository(mihaiDolghan.getId(), devo2.getId(), toDate(september2015.minusYears(1)), null);
		playerToTeamRepository(oscar.getId(), devo2.getId(), toDate(september2015.minusYears(1)), null);
		playerToTeamRepository(borja.getId(), devo2.getId(), toDate(september2015.minusYears(1)), null);
		playerToTeamRepository(pedro.getId(), devo2.getId(), toDate(september2015.minusYears(1)), null);
		playerToTeamRepository(dennis.getId(), devo2.getId(), toDate(september2015.minusYears(1)), null);
		playerToTeamRepository(karim.getId(), devo2.getId(), toDate(september2015.minusYears(1)), null);
		playerToTeamRepository(theo.getId(), devo2.getId(), toDate(september2015.minusYears(1)), null);
		playerToTeamRepository(daan.getId(), devo2.getId(), toDate(september2015.minusYears(1)), null);
		playerToTeamRepository(tomasVirkick.getId(), devo2.getId(), toDate(september2015.minusYears(1)), null);
		playerToTeamRepository(tomasZ.getId(), devo2.getId(), toDate(september2015.minusYears(1)), null);
		playerToTeamRepository(kuba.getId(), devo2.getId(), toDate(september2015.minusYears(1)), null);

	}

	private Team createTeam(final TeamSport sport, final String name, final String location, final Date fromDate,
			final Optional<Date> optionalToDate) {
		final Team team = Team.builder().name(name).location(location).sport(sport.getName())
				.fromDate(fromDate).toDate(optionalToDate.orElse(null)).build();
		return teamCommandService.save(team);
	}

	private Player createPlayer(final String name, final String nickname, final Optional<Character> sex,
			final String bornAddress) {
		final Player player = Player.builder().name(name).nickname(nickname).sex(sex.orElse(null))
				.bornAddress(bornAddress)
				.build();
		return playerRepository.save(player);
	}

	private void playerToTeamRepository(final String playerId, final String teamId, final Date startDate,
			final Date endDate) {
		final PlayerToTeam playerToTeam = PlayerToTeam.builder().playerId(playerId).teamId(teamId)
				.fromDate(startDate).toDate(endDate).build();
		playerToTeamRepository.save(playerToTeam);

	}

	private void addPlayerDetails(final String playerId, final String bio, final TeamSportPosition mainPosition,
			final Set<TeamSportPosition> othersPositions) {
		final PlayerToTeamSportDetails plyaerDetails = new PlayerToTeamSportDetails(playerId,
				mainPosition.sport().getName(), bio, mainPosition.getAbbreviation(), othersPositions.stream()
						.map(otherPosition -> otherPosition.getAbbreviation()).collect(Collectors.toSet()));
		playerToTeamSportDetailsRepository.save(plyaerDetails);
	}

	private Date toDate(final LocalDate localDate) {
		return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

}
