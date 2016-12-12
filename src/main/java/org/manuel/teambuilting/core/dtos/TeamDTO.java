/**
 * 
 */
package org.manuel.teambuilting.core.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.mongodb.annotations.Immutable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.manuel.teambuilting.core.model.TeamId;

import javax.validation.constraints.NotNull;

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
public class TeamDTO {

	private final TeamId id;
	
	@NotNull
	private final String sport;

}
