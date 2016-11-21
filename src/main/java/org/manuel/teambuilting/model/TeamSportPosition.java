package org.manuel.teambuilting.model;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public interface TeamSportPosition {

	String getAbbreviation();

	TeamSportPosition getEnumValue(String positionName);

	TeamSport sport();

}
