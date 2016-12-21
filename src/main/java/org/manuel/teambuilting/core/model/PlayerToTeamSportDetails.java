/**
 * 
 */
package org.manuel.teambuilting.core.model;

import com.mongodb.annotations.Immutable;

import java.util.Set;

import javax.validation.constraints.NotNull;

import org.manuel.teambuilting.core.validations.PlayerExists;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author Manuel Doncel Martos
 *
 */
@Document
@Immutable
@Getter
@lombok.Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PlayerToTeamSportDetails {

	@Id
	private String id;
	
	@NotNull
	@PlayerExists
	private String playerId;
	
	@NotNull
	@Indexed
	private String sport;
	
	private String bio;
	
	@NotNull
	@Indexed
	private String mainPosition;
	
	private Set<String> otherPositions;

	@PersistenceConstructor
	public PlayerToTeamSportDetails(final String playerId, final String sport, final String bio, final String mainPosition, final Set<String> otherPositions) {
		this.playerId = playerId;
		this.sport = sport;
		this.bio = bio; 
		this.mainPosition = mainPosition;
		this.otherPositions = otherPositions;
	}

}