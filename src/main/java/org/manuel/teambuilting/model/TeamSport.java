package org.manuel.teambuilting.model;

import java.util.function.Function;

import org.manuel.teambuilting.model.football.FootballPosition;
import org.manuel.teambuilting.model.football.FutsalPosition;

import lombok.Getter;

@Getter
public enum TeamSport {

	FOOTBALL("Football", FootballPosition.values()),
	FUTSAL("Futsal", FutsalPosition.values());

	private final String name;
	private final TeamSportPosition[] sportPositions;
	
	TeamSport(final String name, final TeamSportPosition[] sportPositions) {
		this.name = name;
		this.sportPositions = sportPositions;
	}
	
	public Function<String, TeamSportPosition> getSportPosition() {
		return positionName -> sportPositions[0].getEnumValue(positionName);
	}
}
