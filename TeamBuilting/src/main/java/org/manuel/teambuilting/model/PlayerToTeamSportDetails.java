/**
 * 
 */
package org.manuel.teambuilting.model;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.manuel.teambuilting.model.football.FootballPosition;
import org.manuel.teambuilting.model.football.TeamSportPosition;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.annotations.Immutable;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Manuel Doncel Martos
 *
 */
@Document
@Immutable
@Getter
@lombok.Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PlayerToTeamSportDetails {

	@Id
	private String id;
	
	@NotNull
	private String sport;
	
	@NotNull
	private final String playerId;
	
	public final String bio;
	
	@NotNull
	@Indexed
	private final TeamSportPosition mainPosition;
	
	private final List<TeamSportPosition> otherPositions;
	
	@PersistenceConstructor
	public PlayerToTeamSportDetails(final String playerId, final String sport, final String bio, final TeamSportPosition mainPosition, final List<TeamSportPosition> otherPositions) {
		this.playerId = playerId;
		this.sport = sport;
		this.bio = bio;
		this.mainPosition = mainPosition;
		this.otherPositions = otherPositions;
	}
	
	public static class Builder {
	}

}
