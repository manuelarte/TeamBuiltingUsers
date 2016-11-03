/**
 * 
 */
package org.manuel.teambuilting.dtos;

import java.util.Date;

import org.manuel.teambuilting.model.PlayerId;
import org.manuel.teambuilting.model.PlayerToTeamId;
import org.manuel.teambuilting.model.TeamId;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.mongodb.annotations.Immutable;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Manuel Doncel Martos
 *
 */
@Immutable
@JsonIgnoreProperties
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(builder = PlayerToTeamDTO.Builder.class)
@Getter
@lombok.Builder
@AllArgsConstructor
public class PlayerToTeamDTO {

	private final PlayerToTeamId id;

	private final PlayerId playerId;

	private final TeamId teamId;

	private final Date startDate;

	private final Date endDate;

	public static class Builder {
	}

}
