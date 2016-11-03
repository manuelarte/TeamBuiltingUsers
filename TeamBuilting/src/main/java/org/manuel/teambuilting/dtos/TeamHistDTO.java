package org.manuel.teambuilting.dtos;

import java.util.Date;

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
@JsonDeserialize(builder = TeamHistDTO.Builder.class)
@Getter
@lombok.Builder
@AllArgsConstructor
public class TeamHistDTO {

	private final TeamHistId id;
	private final TeamId teamId;
	private final String name;
	private final String location;
	private final String emblemPath;
	private final Date fromDate;
	private final Date toDate;

	public static class Builder {
	}

}
