/**
 * 
 */
package org.manuel.teambuilting.model;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.annotations.Immutable;

/**
 * @author Manuel Doncel Martos
 *
 */
@Immutable
@Document
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

	@PersistenceConstructor
	public PlayerToTeam(final String playerId, final String teamId, final Date startDate, final Date endDate) {
		this.playerId = playerId;
		this.teamId = teamId;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	private PlayerToTeam(final Builder builder) {
		this(builder.playerId, builder.teamId, builder.startDate, builder.endDate);
		this.id = builder.id;
	}

	public String getId() {
		return id;
	}

	public String getPlayerId() {
		return playerId;
	}

	public String getTeamId() {
		return teamId;
	}

	public Date getStartDate() {
		return startDate;
	}

	@Null
	public Date getEndDate() {
		return endDate;
	}

	public static class Builder {
		private String id;
		private String playerId;
		private String teamId;
		private Date startDate;
		@Null
		private Date endDate;

		public Builder withId(final String id) {
			this.id = id;
			return this;
		}

		public Builder withPlayerId(final String playerId) {
			this.playerId = playerId;
			return this;
		}

		public Builder withTeamId(final String teamId) {
			this.teamId = teamId;
			return this;
		}

		public Builder withStartDate(final Date startDate) {
			this.startDate = startDate;
			return this;
		}

		public Builder withEndDate(final Date endDate) {
			this.endDate = endDate;
			return this;
		}

		public PlayerToTeam build() {
			return new PlayerToTeam(this);
		}

	}


}
