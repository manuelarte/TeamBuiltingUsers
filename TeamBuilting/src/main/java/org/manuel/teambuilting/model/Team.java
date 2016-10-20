/**
 * 
 */
package org.manuel.teambuilting.model;

import java.util.Collection;

import com.mongodb.annotations.Immutable;

/**
 * @author Manuel Doncel Martos
 *
 */
@Immutable
public class Team {
	
	private final String id;
	private final String name;
	private final Collection<Player> players;
	
	public Team(final Builder builder) {
		this.id = builder.id;
		this.name = builder.name;
		this. players = builder.players;
	}

	public String getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public Collection<Player> getPlayers() {
		return players;
	}
	
	
	public static class Builder {
		private String id;
		private String name;
		private Collection<Player> players;
		
		public Builder withId(final String id) {
			this.id = id;
			return this;
		}
		
		public Builder withName(final String name) {
			this.name = name;
			return this;
		}
		
		public Builder withPlayers(final Collection<Player> players) {
			this.players = players;
			return this;
		}
		
		public Team build() {
			return new Team(this);
		}
	}

}
