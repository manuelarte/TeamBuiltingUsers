package org.manuel.teambuilting.core.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.mongodb.annotations.Immutable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.manuel.teambuilting.core.model.TeamHistId;
import org.manuel.teambuilting.core.model.TeamId;
import org.manuel.teambuilting.core.validations.TeamExists;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Immutable
@JsonIgnoreProperties
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize
@Getter
@lombok.Builder
@AllArgsConstructor
public class TeamHistDTO {

	private final TeamHistId id;
	@NotNull
	@TeamExists
	private final TeamId teamId;
	@NotNull
	private final String name;
	
	@NotNull
	private final String sport;
	
	private final String location;
	@NotNull
	private final Date fromDate;
	private final Date toDate;

}