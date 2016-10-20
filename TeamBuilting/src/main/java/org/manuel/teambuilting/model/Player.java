/**
 * 
 */
package org.manuel.teambuilting.model;

import org.springframework.data.annotation.Id;

import com.mongodb.annotations.Immutable;

/**
 * @author Manuel Doncel Martos
 *
 */
@Immutable
public class Player {
	
	@Id
	private String id;
	
	private String name;
	
	private String bornAddress;

	public Player() {

	}

	private Player(final Builder builder) {
		this.id = builder.id;
		this.name = builder.name;
		this.bornAddress = builder.bornAddress;
	}
	
	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getBornAddress() {
		return bornAddress;
	}
	
	public static class Builder {
		private String id;
		private String name;
		private String bornAddress;
		
		public Builder withId(final String id) {
			this.id = id;
			return this;
		}
		
		public Builder withName(final String name) {
			this.name = name;
			return this;
		}
		
		public Builder withBornAddress(final String bornAddress) {
			this.bornAddress = bornAddress;
			return this;
		}
		
		public Player build() {
			return new Player(this);
		}
	}

}
