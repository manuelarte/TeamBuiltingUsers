package org.manuel.teambuilting.model.football;

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
	
	private final String name;

	FutsalPosition(final String name) {
		this.name = name;
	}
	
}
