/**
 * 
 */
package org.manuel.teambuilting.core.dtos;

import java.util.Set;

import javax.validation.constraints.NotNull;

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
@JsonDeserialize
@Getter
@lombok.Builder(toBuilder = true)
@AllArgsConstructor
public class PlayerToTeamSportDetailsDTO {

	private final String id;
	
	@NotNull
	private final String playerId;
	
	@NotNull
	private final String sport;
	
	public final String bio;
	
	@NotNull
	private final String mainPosition;
	
	private final Set<String> otherPositions;

}
