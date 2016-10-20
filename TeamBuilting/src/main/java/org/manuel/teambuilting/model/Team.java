/**
 * 
 */
package org.manuel.teambuilting.model;

import com.mongodb.annotations.Immutable;

/**
 * @author Manuel Doncel Martos
 *
 */
@Immutable
public class Team {
	
	private String id;

	public Team() {
	}

	private Team(final Builder builder) {
		this.id = builder.id;
	}

	public String getId() {
		return id;
	}
	
	public static class Builder {
		private String id;
		
		public Builder withId(final String id) {
			this.id = id;
			return this;
		}
		
		public Team build() {
			return new Team(this);
		}
	}

}
