/**
 * 
 */
package org.manuel.teambuilting.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.annotations.Immutable;

import lombok.Getter;

/**
 * @author Manuel Doncel Martos
 *
 */
@Immutable
@Document
@Getter
public class Team {
	
	@Id
	private String id;

	@PersistenceConstructor
	public Team() {
	}

	private Team(final Builder builder) {
		this.id = builder.id;
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
