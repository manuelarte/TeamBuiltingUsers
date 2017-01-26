package org.manuel.teambuilting.core.integration;

import java.util.Calendar;
import java.util.Date;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.manuel.teambuilting.core.exceptions.ValidationRuntimeException;
import org.manuel.teambuilting.core.model.Player;
import org.manuel.teambuilting.core.model.PlayerToTeam;
import org.manuel.teambuilting.core.model.Team;
import org.manuel.teambuilting.core.repositories.PlayerRepository;
import org.manuel.teambuilting.core.repositories.PlayerToTeamRepository;
import org.manuel.teambuilting.core.repositories.TeamRepository;
import org.manuel.teambuilting.core.services.PlayerToTeamService;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Test Suit to check that it is not possible to store wrong player history
 *
 * @author manuel.doncel.martos
 * @since 13-1-2017
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class PlayerHistoryTest {

	@Inject
	private PlayerRepository playerRepository;

	@Inject
	private TeamRepository teamRepository;

	@Inject
	private PlayerToTeamRepository playerToTeamRepository;

	@Inject
	private PlayerToTeamService playerToTeamService;

	@Test(expected = ValidationRuntimeException.class)
	public void testSaveAnotherEntryForTheSameTeamAndInsideTimeFrame() {
		final Player player = playerRepository.save(new Player("name", "nickname",
			'M', "address", "imageLink"));
		final Date teamToDate = new Date();
		final Date teamFromDate = changeDate(teamToDate, -2, Calendar.YEAR);
		final Team team = teamRepository.save(new Team("name", "location", "sport", "bio", teamFromDate, teamToDate));

		final Date playerToTeamFromDate = teamFromDate;
		final Date playerToTeamToDate = teamToDate;
		playerToTeamRepository.save(new PlayerToTeam(player.getId(), team.getId(), playerToTeamFromDate, playerToTeamToDate));
		// That's original Setup, one player playing during all the history of the team, now we try to add another entry
		final Date newPlayerToTeamFromDate = changeDate(playerToTeamFromDate, +1, Calendar.MONTH);
		final Date newPlayerToTeamToDate = changeDate(playerToTeamToDate, -1, Calendar.MONTH);
		final PlayerToTeam notAllowedEntry = new PlayerToTeam(player.getId(), team.getId(), newPlayerToTeamFromDate, newPlayerToTeamToDate);
		playerToTeamService.savePlayerToTeam(notAllowedEntry);
	}

	@Test(expected = ValidationRuntimeException.class)
	public void testCannotStorePlayerHistoryAfterEndOfTheTeam() {
		final Player player = playerRepository.save(new Player("name", "nickname",
			'M', "address", "imageLink"));
		final Date teamToDate = new Date();
		final Date teamFromDate = changeDate(teamToDate, -2, Calendar.YEAR);
		final Team team = teamRepository.save(new Team("name", "location", "sport", "bio", teamFromDate, teamToDate));

		final Date playerToTeamToDate = changeDate(teamToDate, +1, Calendar.DAY_OF_MONTH);
		final Date playerToTeamFromDate = teamFromDate;
		final PlayerToTeam playerToTeam = new PlayerToTeam(player.getId(), team.getId(), playerToTeamFromDate, playerToTeamToDate);
		playerToTeamService.savePlayerToTeam(playerToTeam);
	}

	private Date changeDate(final Date date, final int number, final int calendarField ) {
		final Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(calendarField, number);
		return cal.getTime();
	}

}
