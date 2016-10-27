/**
 * 
 */
package org.manuel.teambuilting.model;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.annotations.Immutable;

/**
 * @author Manuel Doncel Martos
 *
 */
@Immutable
@Document
public class Player {
	
	@Id
	private String id;
	
	@NotNull
	@Indexed
	private final String name;
	
	private final String bornAddress;

	@PersistenceConstructor
	public Player(final String name, final String bornAddress) {
		this.name = name;
		this.bornAddress = bornAddress;
	}

	private Player(final Builder builder) {
		this(builder.name, builder.bornAddress);
		this.id = builder.id;
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
