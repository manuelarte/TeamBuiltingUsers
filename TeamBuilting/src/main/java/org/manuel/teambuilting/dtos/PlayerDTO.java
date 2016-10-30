/**
 * 
 */
package org.manuel.teambuilting.dtos;

import org.manuel.teambuilting.model.PlayerId;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.mongodb.annotations.Immutable;

/**
 * @author Manuel Doncel Martos
 *
 */
@Immutable
@JsonIgnoreProperties
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(builder = PlayerDTO.Builder.class)
public class PlayerDTO {
	
	private final PlayerId id;
	
	private final String name;
	
	private final String nickname;

	private final String bornAddress;

	public PlayerDTO(final Builder builder) {
		this.id = builder.id;
		this.name = builder.name;
		this.nickname = builder.nickname;
		this.bornAddress = builder.bornAddress;
	}
	
	public PlayerId getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getNickname() {
		return nickname;
	}

	public String getBornAddress() {
		return bornAddress;
	}
	
	public static class Builder {
		private PlayerId id;
		private String name;
		private String nickname;
		private String bornAddress;
		
		public Builder withId(final PlayerId id) {
			this.id = id;
			return this;
		}
		
		public Builder withName(final String name) {
			this.name = name;
			return this;
		}
		
		public Builder withNickname(final String nickname) {
			this.nickname = nickname;
			return this;
		}

		public Builder withBornAddress(final String bornAddress) {
			this.bornAddress = bornAddress;
			return this;
		}
		
		public PlayerDTO build() {
			return new PlayerDTO(this);
		}
	}

}