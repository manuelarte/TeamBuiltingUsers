/**
 * 
 */
package org.manuel.teambuilting.model;

import java.util.Date;

import org.springframework.data.annotation.Id;

/**
 * @author Manuel Doncel Martos
 *
 */
public class TeamHist {

	@Id
	private String id;
	private String teamId;
	private String name;
	private Date fromDate;
	private Date toDate;
	// emblem

	public TeamHist() {
	}

	public TeamHist(final Builder builder) {
		this.id = builder.id;
		this.teamId = builder.teamId;
		this.name = builder.name;
		this.fromDate = builder.fromDate;
		this.toDate = builder.toDate;
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
