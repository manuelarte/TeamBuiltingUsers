/**
 * 
 */
package org.manuel.teambuilting.core.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.mongodb.annotations.Immutable;

import java.util.Date;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;

import org.manuel.teambuilting.core.model.PlayerId;
import org.manuel.teambuilting.core.model.PlayerToTeamId;
import org.manuel.teambuilting.core.model.TeamId;
import org.manuel.teambuilting.core.validations.TeamExists;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Manuel Doncel Martos
 *
 */
@Immutable
@JsonIgnoreProperties
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize
@Getter
@lombok.Builder
@AllArgsConstructor
public class PlayerToTeamDTO {

	private final PlayerToTeamId id;

	@NotNull
	private final PlayerId playerId;

	@NotNull
	@TeamExists
	private final TeamId teamId;

	@NotNull
	private final Date fromDate;

	private final Date toDate;
	
	@AssertTrue
	private boolean startDateBeforeEndDate() {
		return toDate == null ? true : toDate.getTime() > fromDate.getTime();
	}

}
