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
	private String name;

	public Team() {
	}

	private Team(final Builder builder) {
		this.id = builder.id;
		this.name = builder.name;
	}

	public String getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	
	public static class Builder {
		private String id;
		private String name;
		
		public Builder withId(final String id) {
			this.id = id;
			return this;
		}
		
		public Builder withName(final String name) {
			this.name = name;
			return this;
		}
		
		public Team build() {
			return new Team(this);
		}
	}

}
