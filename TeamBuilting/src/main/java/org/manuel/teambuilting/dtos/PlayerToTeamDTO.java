/**
 * 
 */
package org.manuel.teambuilting.dtos;

import java.util.Date;

import org.manuel.teambuilting.model.PlayerId;
import org.manuel.teambuilting.model.PlayerToTeamId;
import org.manuel.teambuilting.model.TeamId;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.mongodb.annotations.Immutable;
import com.sun.istack.internal.Nullable;

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

	private final Date startDate;

	@Nullable
	private final Date endDate;

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

	public Date getStartDate() {
		return startDate;
	}

	@Nullable
	public Date getEndDate() {
		return endDate;
	}

	public static class Builder {
		private PlayerToTeamId id;
		private PlayerId playerId;
		private TeamId teamId;
		private Date startDate;
		@Nullable
		private Date endDate;

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

		public Builder withStartDate(final Date startDate) {
			this.startDate = startDate;
			return this;
		}

		public Builder withEndDate(@Nullable final Date endDate) {
			this.endDate = endDate;
			return this;
		}

		public PlayerToTeamDTO build() {
			return new PlayerToTeamDTO(this);
		}
	}

}
