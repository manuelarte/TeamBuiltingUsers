package org.manuel.teambuilting.model;

public interface TeamSportPosition {

	String getAbbreviation();

	TeamSportPosition getEnumValue(String positionName);

	TeamSport sport();

}
