/**
 * 
 */
package org.manuel.teambuilting.dtos;

import org.springframework.data.annotation.Id;

import com.mongodb.annotations.Immutable;

/**
 * @author Manuel Doncel Martos
 *
 */
@Immutable
public class Player {
	
	@Id
	private final PlayerId id;
	
	private final String name;
	
	private final String address;

	public Player(final Builder builder) {
		this.id = builder.id;
		this.name = builder.name;
		this.address = builder.address;
	}
	
	public PlayerId getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}
	
	public static class Builder {
		private PlayerId id;
		private String name;
		private String address;
		
		public Builder withId(final PlayerId id) {
			this.id = id;
			return this;
		}
		
		public Builder withName(final String name) {
			this.name = name;
			return this;
		}
		
		public Builder withAddress(final String address) {
			this.address = address;
			return this;
		}
		
		public Player build() {
			return new Player(this);
		}
	}

}
