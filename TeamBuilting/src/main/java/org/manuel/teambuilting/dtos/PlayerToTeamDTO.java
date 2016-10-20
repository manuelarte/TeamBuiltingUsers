/**
 * 
 */
package org.manuel.teambuilting.dtos;

import java.time.LocalDate;

import org.manuel.teambuilting.model.PlayerId;
import org.manuel.teambuilting.model.PlayerToTeamId;
import org.manuel.teambuilting.model.TeamId;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.mongodb.annotations.Immutable;

/**
 * @author Manuel Doncel Martos
 *
 */
@Immutable
@JsonIgnoreProperties
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(builder = PlayerToTeamDTO.Builder.class)
public class PlayerToTeamDTO {

	private final PlayerToTeamId id;

	private final PlayerId playerId;

	private final TeamId teamId;

	private final LocalDate startDate;

	private final LocalDate endDate;

	public PlayerToTeamDTO(final Builder builder) {
		this.id = builder.id;
		this.playerId = builder.playerId;
		this.teamId = builder.teamId;
		this.startDate = builder.startDate;
		this.endDate = builder.endDate;
	}

	public PlayerToTeamId getId() {
		return id;
	}

	public PlayerId getPlayerId() {
		return playerId;
	}

	public TeamId getTeamId() {
		return teamId;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public static class Builder {
		private PlayerToTeamId id;
		private PlayerId playerId;
		private TeamId teamId;
		private LocalDate startDate;
		private LocalDate endDate;

		public Builder withId(final PlayerToTeamId id) {
			this.id = id;
			return this;
		}

		public Builder withPlayerId(final PlayerId playerId) {
			this.playerId = playerId;
			return this;
		}

		public Builder withTeamId(final TeamId teamId) {
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

		public PlayerToTeamDTO build() {
			return new PlayerToTeamDTO(this);
		}
	}

}
