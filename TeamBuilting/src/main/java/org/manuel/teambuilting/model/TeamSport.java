package org.manuel.teambuilting.model;

import org.manuel.teambuilting.model.football.FootballPosition;
import org.manuel.teambuilting.model.football.FutsalPosition;
import org.manuel.teambuilting.model.football.TeamSportPosition;

import lombok.Getter;

@Getter
public enum TeamSport {

	FOOTBALL("Football", FootballPosition.class),
	FUTSAL("Futsal", FutsalPosition.class);

	private final String name;
	private final Class<? extends TeamSportPosition> sportPositions;
	
	TeamSport(final String name, final Class<? extends TeamSportPosition> sportPositions) {
		this.name = name;
		this.sportPositions = sportPositions;
	}
}
