/**
 * 
 */
package org.manuel.teambuilting.dtos;

import javax.validation.constraints.NotNull;

import org.manuel.teambuilting.model.TeamId;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.mongodb.annotations.Immutable;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author Manuel Doncel Martos
 *
 */
@Immutable
@JsonIgnoreProperties
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(builder = TeamDTO.TeamDTOBuilder.class)
@Getter
@lombok.Builder
@RequiredArgsConstructor
public class TeamDTO {
	
	private final TeamId id;
	
	@NotNull
	private final String sport;

}
