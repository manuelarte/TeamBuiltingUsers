/**
 * 
 */
package org.manuel.teambuilting.core.model;

import com.mongodb.annotations.Immutable;

import java.util.Date;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Manuel Doncel Martos
 *
 */
@Immutable
@Document
@Getter
@lombok.Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PlayerToTeam {

	@Id
	private String id;

	@NotNull
	@Indexed
	private final String playerId;

	@NotNull
	@Indexed
	private final String teamId;

	@NotNull
	private final Date fromDate;

	private final Date toDate;
	
	@AssertTrue
	private boolean startDateBeforeEndDate() {
		return toDate == null ? true : toDate.getTime() > fromDate.getTime();
	}

	@PersistenceConstructor
	public PlayerToTeam(final String playerId, final String teamId, final Date fromDate, final Date toDate) {
		this.playerId = playerId;
		this.teamId = teamId;
		this.fromDate = fromDate;
		this.toDate = toDate;
	}
	
}
