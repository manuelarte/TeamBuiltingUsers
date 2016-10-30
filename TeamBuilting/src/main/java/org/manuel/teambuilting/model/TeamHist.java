/**
 * 
 */
package org.manuel.teambuilting.model;

import java.util.Date;

import javax.validation.constraints.NotNull;

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
public class TeamHist {

	@Id
	private String id;
	@NotNull
	@Indexed
	private final String teamId;
	@Indexed
	private final String name;
	private final String location;
	private final String emblemPath;
	private final Date fromDate;
	private final Date toDate;
	// emblem

	@PersistenceConstructor
	public TeamHist(final String teamId, final String name, final String location, final String emblemPath,
			final Date fromDate,
			final Date toDate) {
		this.teamId = teamId;
		this.name = name;
		this.location = location;
		this.emblemPath = emblemPath;
		this.fromDate = fromDate;
		this.toDate = toDate;
	}

	public TeamHist(final Builder builder) {
		this(builder.teamId, builder.name, builder.location, builder.emblemPath, builder.fromDate, builder.toDate);
		this.id = builder.id;
	}

	public String getId() {
		return id;
	}

	public String getTeamId() {
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

		private String id;
		private String teamId;
		private String name;
		private String location;
		private String emblemPath;
		private Date fromDate;
		private Date toDate;

		public Builder withId(final String id) {
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

		public Builder withTeamId(final String teamId) {
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

		public TeamHist build() {
			return new TeamHist(this);
		}
	}

}
