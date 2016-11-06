package org.manuel.teambuilting.model.football;

import org.manuel.teambuilting.model.TeamSport;
import org.manuel.teambuilting.model.TeamSportPosition;

import lombok.Getter;

/**
 * 
 * @author Manuel Doncel Martos
 *
 */
@Getter
public enum FutsalPosition implements TeamSportPosition {

	GK("goalkeeper"),
	LD("Left Defender"),
	DF("Defender"),
	RD("Right Defender"),
	LW("Left winger"),
	RW("Right winger"),
	PV("Pivot"),
	UNIVERSAL("Universal");
	
	private static final TeamSport SPORT = TeamSport.FUTSAL;
	
	private final String name;

	FutsalPosition(final String name) {
		this.name = name;
	}
	
	@Override
	public TeamSport sport() {
		return SPORT;
	}

	@Override
	public String getAbbreviation() {
		return name();
	}

	@Override
	public TeamSportPosition getEnumValue(String positionName) {
		return FutsalPosition.valueOf(positionName);
	}
	
}
