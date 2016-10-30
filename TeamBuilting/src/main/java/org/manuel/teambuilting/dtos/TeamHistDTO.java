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
	private final String location;
	private final String emblemPath;
	private final Date fromDate;
	private final Date toDate;

	public TeamHistDTO(final Builder builder) {
		this.id = builder.id;
		this.teamId = builder.teamId;
		this.name = builder.name;
		this.location = builder.location;
		this.emblemPath = builder.emblemPath;
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

	public String getLocation() {
		return location;
	}

	public String getEmblemPath() {
		return emblemPath;
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
		private String location;
		private String emblemPath;
		private Date fromDate;
		private Date toDate;

		public Builder() {

		}

		public Builder(final TeamHistDTO toCopy) {
			this.id = toCopy.id;
			this.teamId = toCopy.teamId;
			this.name = toCopy.name;
			this.location = toCopy.location;
			this.emblemPath = toCopy.emblemPath;
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

		public Builder withLocation(final String location) {
			this.location = location;
			return this;
		}

		public Builder withEmblemPath(final String emblemPath) {
			this.emblemPath = emblemPath;
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
