/**
 * 
 */
package org.manuel.teambuilting.dtos;

import java.util.Collection;

import org.manuel.teambuilting.model.TeamId;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.mongodb.annotations.Immutable;

/**
 * @author Manuel Doncel Martos
 *
 */
@Immutable
@JsonIgnoreProperties
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(builder = TeamDTO.Builder.class)
public class TeamDTO {
	
	private final TeamId id;
	private final String name;
	private final Collection<PlayerDTO> players;
	
	public TeamDTO(final Builder builder) {
		this.id = builder.id;
		this.name = builder.name;
		this. players = builder.players;
	}

	public TeamId getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public Collection<PlayerDTO> getPlayers() {
		return players;
	}
	
	
	@JsonPOJOBuilder(buildMethodName = "build", withPrefix = "with")
	public static class Builder {
		private TeamId id;
		private String name;
		private Collection<PlayerDTO> players;
		
		public Builder withId(final TeamId id) {
			this.id = id;
			return this;
		}
		
		public Builder withName(final String name) {
			this.name = name;
			return this;
		}
		
		public Builder withPlayers(final Collection<PlayerDTO> players) {
			this.players = players;
			return this;
		}
		
		public TeamDTO build() {
			return new TeamDTO(this);
		}
	}

}
