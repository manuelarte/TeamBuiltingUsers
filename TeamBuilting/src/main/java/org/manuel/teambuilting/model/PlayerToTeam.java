/**
 * 
 */
package org.manuel.teambuilting.model;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
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
	private String playerId;
	@NotNull
	private String teamId;
	@NotNull
	private LocalDate startDate;
	@NotNull
	private LocalDate endDate;

	public PlayerToTeam() {
	}

	private PlayerToTeam(final Builder builder) {
		this.id = builder.id;
		this.playerId = builder.playerId;
		this.teamId = builder.teamId;
		this.startDate = builder.startDate;
		this.endDate = builder.endDate;
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

	public LocalDate getStartDate() {
		return startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public static class Builder {
		private String id;
		private String playerId;
		private String teamId;
		private LocalDate startDate;
		private LocalDate endDate;

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

		public Builder withStartDate(final LocalDate startDate) {
			this.startDate = startDate;
			return this;
		}

		public Builder withEndDate(final LocalDate endDate) {
			this.endDate = endDate;
			return this;
		}

		public PlayerToTeam build() {
			return new PlayerToTeam(this);
		}

	}


}
