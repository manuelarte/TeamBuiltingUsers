package org.manuel.teambuilting.model.football;

import lombok.Getter;

/**
 * 
 * @author Manuel Doncel Martos
 *
 */
@Getter
public enum FootballPosition implements TeamSportPosition {

	GK("goalkeeper"),
	LB("Left Back"),
	LCB("Left Center Back"),
	CB("Center-back"),
	RCB("Right Center Back"),
	RB("Right Back"),
	LM("Left Midfielder"),
	LCM("Left Centre Midfield"),
	CDM("Centre Defensive Midfield"),
	CM("Centre Midfield (back and forward)"),
	CAM("Centre Attacking  Midfield"),
	RCM("Right Centre Midfield"),
	RM("Right Midfield"),
	ST("Striker"),
	CF("Center forward"),
	LW("Left winger"),
	RW("Right winger");

	private final String name;

	FootballPosition(final String name) {
		this.name = name;
	}
	
}
