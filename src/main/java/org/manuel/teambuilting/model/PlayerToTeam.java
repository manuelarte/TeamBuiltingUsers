/**
 * 
 */
package org.manuel.teambuilting.model;

import java.util.Date;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;

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
	private final Date startDate;

	private final Date endDate;
	
	@AssertTrue
	private boolean startDateBeforeEndDate() {
		return endDate == null ? true : endDate.getTime() > startDate.getTime();
	}

	@PersistenceConstructor
	public PlayerToTeam(final String playerId, final String teamId, final Date startDate, final Date endDate) {
		this.playerId = playerId;
		this.teamId = teamId;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public static class Builder {
	}

	
}
