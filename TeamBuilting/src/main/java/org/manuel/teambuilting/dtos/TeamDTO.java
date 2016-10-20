/**
 * 
 */
package org.manuel.teambuilting.dtos;

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
	
	public TeamDTO(final Builder builder) {
		this.id = builder.id;
	}

	public TeamId getId() {
		return id;
	}
	
	@JsonPOJOBuilder(buildMethodName = "build", withPrefix = "with")
	public static class Builder {
		private TeamId id;
		
		public Builder withId(final TeamId id) {
			this.id = id;
			return this;
		}
		
		public TeamDTO build() {
			return new TeamDTO(this);
		}
	}

}
