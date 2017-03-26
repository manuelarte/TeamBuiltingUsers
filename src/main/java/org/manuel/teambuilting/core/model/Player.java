/**
 * 
 */
package org.manuel.teambuilting.core.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.mongodb.annotations.Immutable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Manuel Doncel Martos
 *
 */
@Immutable
@Document
@Data
@lombok.Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@JsonIgnoreProperties
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize
public class Player {
	
	@Id
	private String id;
	
	@NotNull
	@Indexed
	@Size(min=2, max=200)
	private String name;
	
	@Indexed
	@Size(min=2, max=50)
	private String nickname;
	
	@Indexed
	private Character sex;

	@Size(min=5, max=200)
	private String bornAddress;

	@Size(min=10, max=200)
	private String imageLink;

	@PersistenceConstructor
	public Player(final String name, final String nickname, final Character sex, final String bornAddress, final String imageLink) {
		this.name = name;
		this.nickname = nickname;
		this.sex = sex;
		this.bornAddress = bornAddress;
		this.imageLink = imageLink;
	}

}