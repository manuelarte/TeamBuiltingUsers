/**
 * 
 */
package org.manuel.teambuilting.core.model;

import com.mongodb.annotations.Immutable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.manuel.teambuilting.core.validations.PlayerExists;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * @author Manuel Doncel Martos
 *
 */
@Document
@Immutable
@Getter
@lombok.Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PlayerToTeamSportDetails {

	@Id
	private String id;
	
	@NotNull
	@PlayerExists
	private String playerId;
	
	@NotNull
	@Indexed(unique=true)
	private String sport;
	
	private String bio;
	
	@NotNull
	@Indexed
	private String mainPosition;
	
	private Set<String> otherPositions;

	public PlayerToTeamSportDetails() {

	}

	@PersistenceConstructor
	public PlayerToTeamSportDetails(final String playerId, final String sport, final String bio, final String mainPosition, final Set<String> otherPositions) {
		this.playerId = playerId;
		this.sport = sport;
		this.bio = bio; 
		this.mainPosition = mainPosition;
		this.otherPositions = otherPositions;
	}

}