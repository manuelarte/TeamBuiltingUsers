package org.manuel.teambuilting.dtos;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.manuel.teambuilting.model.TeamHistId;
import org.manuel.teambuilting.model.TeamId;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.mongodb.annotations.Immutable;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Immutable
@JsonIgnoreProperties
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(builder = TeamHistDTO.TeamHistDTOBuilder.class)
@Getter
@lombok.Builder
@AllArgsConstructor
public class TeamHistDTO {

	private final TeamHistId id;
	@NotNull
	private final TeamId teamId;
	@NotNull
	private final String name;
	
	@NotNull
	private final String sport;
	
	private final String location;
	private final String emblemPath;
	@NotNull
	private final Date fromDate;
	private final Date toDate;


}