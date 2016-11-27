package org.manuel.teambuilting.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public interface TeamSportPosition {

	String getAbbreviation();

	String getFullName();

	@JsonIgnore
	TeamSportPosition getEnumValue(String positionName);

	TeamSport sport();

}
