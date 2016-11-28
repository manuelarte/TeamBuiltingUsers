package org.manuel.teambuilting.core.model.sports;

import org.manuel.teambuilting.core.model.TeamSport;
import org.manuel.teambuilting.core.model.TeamSportPosition;

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
	public String getFullName() {
		return getName();
	}

	@Override
	public TeamSportPosition getEnumValue(final String positionName) {
		return FutsalPosition.valueOf(positionName);
	}
	
}
