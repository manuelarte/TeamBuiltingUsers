package org.manuel.teambuilting.dtos;

import java.util.Date;

import org.manuel.teambuilting.model.TeamHistId;
import org.manuel.teambuilting.model.TeamId;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.mongodb.annotations.Immutable;

@Immutable
@JsonIgnoreProperties
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(builder = TeamHistDTO.Builder.class)
public class TeamHistDTO {

	private final TeamHistId id;
	private final TeamId teamId;
	private final String name;
	private final Date fromDate;
	private final Date toDate;
	// emblem

	public TeamHistDTO(final Builder builder) {
		this.id = builder.id;
		this.teamId = builder.teamId;
		this.name = builder.name;
		this.fromDate = builder.fromDate;
		this.toDate = builder.toDate;
	}

	public TeamHistId getId() {
		return id;
	}

	public TeamId getTeamId() {
		return teamId;
	}

	public String getName() {
		return name;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public static class Builder {

		private TeamHistId id;
		private TeamId teamId;
		private String name;
		private Date fromDate;
		private Date toDate;

		public Builder() {

		}

		public Builder(final TeamHistDTO toCopy) {
			this.id = toCopy.id;
			this.teamId = toCopy.teamId;
			this.name = toCopy.name;
			this.fromDate = toCopy.fromDate;
			this.toDate = toCopy.toDate;
		}

		public Builder withId(final TeamHistId id) {
			this.id = id;
			return this;
		}

		public Builder withName(final String name) {
			this.name = name;
			return this;
		}

		public Builder withTeamId(final TeamId teamId) {
			this.teamId = teamId;
			return this;
		}

		public Builder withFromDate(final Date fromDate) {
			this.fromDate = fromDate;
			return this;
		}

		public Builder withToDate(final Date endDate) {
			this.toDate = endDate;
			return this;
		}

		public TeamHistDTO build() {
			return new TeamHistDTO(this);
		}
	}

}
