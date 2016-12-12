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
import org.manuel.teambuilting.core.validations.PlayerExists;

import javax.validation.constraints.NotNull;
import java.util.Set;

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
	@PlayerExists
	private final String playerId;
	
	@NotNull
	private final String sport;
	
	public final String bio;
	
	@NotNull
	private final String mainPosition;
	
	private final Set<String> otherPositions;

}
