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

import org.manuel.teambuilting.core.dtos.PlayerDTO;
import org.manuel.teambuilting.core.dtos.PlayerToTeamDTO;
import org.manuel.teambuilting.core.dtos.TeamDTO;
import org.manuel.teambuilting.core.dtos.TeamHistDTO;
import org.manuel.teambuilting.core.model.Player;
import org.manuel.teambuilting.core.model.PlayerId;
import org.manuel.teambuilting.core.model.PlayerToTeamSportDetails;
import org.manuel.teambuilting.core.model.TeamId;
import org.manuel.teambuilting.core.model.TeamSport;
import org.manuel.teambuilting.core.model.TeamSportPosition;
import org.manuel.teambuilting.core.model.repository.PlayerRepository;
import org.manuel.teambuilting.core.model.repository.PlayerToTeamRepository;
import org.manuel.teambuilting.core.model.repository.PlayerToTeamSportDetailsRepository;
import org.manuel.teambuilting.core.services.DTOSConverter;
import org.manuel.teambuilting.core.services.TeamHistService;
import org.manuel.teambuilting.core.services.TeamService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TeamBuiltingCoreApplication implements CommandLineRunner {

	@Inject
	private PlayerRepository playerRepository;

	@Inject
	private TeamService teamService;

	@Inject
	private TeamHistService teamHistService;

	@Inject
	private PlayerToTeamRepository playerToTeamRepository;

	@Inject
	private PlayerToTeamSportDetailsRepository playerToTeamSportDetailsRepository;

	@Inject
	private DTOSConverter dtosConverter;

	public static void main(final String[] args) {
		SpringApplication.run(TeamBuiltingCoreApplication.class, args);
	}

	@Override
	public void run(final String... args) throws Exception {
		//createData();
	}

	private void createData() {
		final PlayerDTO javiLeon = createPlayer("Javier leon", "Javi", Optional.of('M'),
				"Soria, Castille and Leon, Spain");
		final PlayerDTO sanne = createPlayer("Sanne", "Sanne", Optional.of('M'), "Amsterdam, Netherlands");
		final PlayerDTO mihaiDolghan = createPlayer("Mihai Dolghan", "Mihai", Optional.of('M'), "Bucharest, Romania");
		final PlayerDTO oscar = createPlayer("Oscar", "Oscar", Optional.of('M'), "Santander, Cantabria, Spain");
		final PlayerDTO borja = createPlayer("Borja Sacristan", "Borja", Optional.of('M'), "Madrid, Madrid, Spain");
		final PlayerDTO manu = createPlayer("Manuel Doncel Martos", "Manu D", Optional.of('M'),
				"Ubeda, Jaen, 23400 Spain");
		addPlayerDetails(manu.getId(), "Player with offensive vocation", LB,
				new HashSet<TeamSportPosition>(Arrays.asList(LM, CAM, LW)));

		final PlayerDTO pedro = createPlayer("Pedro Dans", "Pedro", Optional.of('M'), "Coruna, Galicia, Spain");
		final PlayerDTO dennis = createPlayer("Dennis Bakker", "Dennis", Optional.of('M'), "Madrid, Madrid, Spain");
		final PlayerDTO karim = createPlayer("Karim", "Karim", Optional.of('M'), "Guadalajara, Madrid, Spain");
		final PlayerDTO diego = createPlayer("Diego Ramonde", "Diego", Optional.of('M'), "Coruna, Galicia, Spain");
		final PlayerDTO nelson = createPlayer("Nelson Alfonso", "Nelson", Optional.of('M'), "Lisbon, Portugal");
		final PlayerDTO theo = createPlayer("Theodor Phantender", "Theo D'Or", Optional.of('M'),
				"Amsterdam, Netherlands");
		final PlayerDTO daniel = createPlayer("Daniel Dittmar", "Daniel", Optional.of('M'), "Sydney, Australia");
		final PlayerDTO daan = createPlayer("Dann Farjon", "Daan", Optional.of('M'), "Amsterdam, Netherlands");
		final PlayerDTO tomasVirkick = createPlayer("Tomas Virkick", "Virco", Optional.of('M'), "Slovakia");
		final PlayerDTO tomasZ = createPlayer("Tomas Z", "Tomas", Optional.of('M'), "Slovakia");
		final PlayerDTO kuba = createPlayer("Kuba", "Kuba", Optional.of('M'), "Krakow, Poland");
		
		final Date startDevo2 = toDate(LocalDate.of(1958, 1, 1));
		final String devoAddress = "Herman Bonpad 4, 1067 SN Amsterdam";
		final TeamHistDTO devo2 = createTeam(TeamSport.FOOTBALL, "Devo'58 Zaterdag 2", devoAddress, startDevo2,
				Optional.empty());
		
		// final Date startDevo2Fake = Date.from(LocalDate.of(1900, 1,
		// 1).atStartOfDay(ZoneId.systemDefault()).toInstant());
		// final TeamHistDTO previousDevo2 = teamHistService.saveTeamHist(new
		// TeamHistDTO.Builder(devo2).withId(null)
		// .withFromDate(startDevo2Fake)
		// .withToDate(startDevo2).build());
		
		final LocalDate september2015 = LocalDate.of(2015, Month.SEPTEMBER.getValue(), 1);
		playerToTeamRepository(manu.getId(), devo2.getTeamId(), toDate(september2015), null);
		playerToTeamRepository(javiLeon.getId(), devo2.getTeamId(), toDate(september2015), null);
		playerToTeamRepository(diego.getId(), devo2.getTeamId(), toDate(september2015), null);
		playerToTeamRepository(nelson.getId(), devo2.getTeamId(), toDate(september2015), null);
		playerToTeamRepository(daniel.getId(), devo2.getTeamId(), toDate(september2015), null);

		playerToTeamRepository(sanne.getId(), devo2.getTeamId(), toDate(september2015.minusYears(1)), null);
		playerToTeamRepository(mihaiDolghan.getId(), devo2.getTeamId(), toDate(september2015.minusYears(1)), null);
		playerToTeamRepository(oscar.getId(), devo2.getTeamId(), toDate(september2015.minusYears(1)), null);
		playerToTeamRepository(borja.getId(), devo2.getTeamId(), toDate(september2015.minusYears(1)), null);
		playerToTeamRepository(pedro.getId(), devo2.getTeamId(), toDate(september2015.minusYears(1)), null);
		playerToTeamRepository(dennis.getId(), devo2.getTeamId(), toDate(september2015.minusYears(1)), null);
		playerToTeamRepository(karim.getId(), devo2.getTeamId(), toDate(september2015.minusYears(1)), null);
		playerToTeamRepository(theo.getId(), devo2.getTeamId(), toDate(september2015.minusYears(1)), null);
		playerToTeamRepository(daan.getId(), devo2.getTeamId(), toDate(september2015.minusYears(1)), null);
		playerToTeamRepository(tomasVirkick.getId(), devo2.getTeamId(), toDate(september2015.minusYears(1)), null);
		playerToTeamRepository(tomasZ.getId(), devo2.getTeamId(), toDate(september2015.minusYears(1)), null);
		playerToTeamRepository(kuba.getId(), devo2.getTeamId(), toDate(september2015.minusYears(1)), null);

	}

	private TeamHistDTO createTeam(final TeamSport sport, final String name, final String location, final Date fromDate,
			final Optional<Date> optionalToDate) {
		TeamDTO team = TeamDTO.builder().sport(sport.getName()).build();
		team = teamService.createTeam(team);
		final TeamHistDTO teamHist = TeamHistDTO.builder().name(name).teamId(team.getId()).location(location)
				.fromDate(fromDate).toDate(optionalToDate.orElse(null)).build();
		return teamHistService.saveTeamHist(teamHist);
	}

	private PlayerDTO createPlayer(final String name, final String nickname, final Optional<Character> sex,
			final String bornAddress) {
		final PlayerDTO player = PlayerDTO.builder().name(name).nickname(nickname).sex(sex.orElse(null))
				.bornAddress(bornAddress)
				.build();
		final Player saved = playerRepository.save(dtosConverter.toPlayer(player));
		return dtosConverter.toPlayerDTO(saved);
	}

	private void playerToTeamRepository(final PlayerId playerId, final TeamId teamId, final Date startDate,
			final Date endDate) {
		final PlayerToTeamDTO playerToTeam = PlayerToTeamDTO.builder().playerId(playerId).teamId(teamId)
				.startDate(startDate).endDate(endDate).build();
		playerToTeamRepository.save(dtosConverter.toPlayerToTeam(playerToTeam));

	}

	private void addPlayerDetails(final PlayerId playerId, final String bio, final TeamSportPosition mainPosition,
			final Set<TeamSportPosition> othersPositions) {
		final PlayerToTeamSportDetails plyaerDetails = new PlayerToTeamSportDetails(playerId.getId(),
				mainPosition.sport().getName(), bio, mainPosition.getAbbreviation(), othersPositions.stream()
						.map(otherPosition -> otherPosition.getAbbreviation()).collect(Collectors.toSet()));
		playerToTeamSportDetailsRepository.save(plyaerDetails);
	}

	private Date toDate(final LocalDate localDate) {
		return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

}
