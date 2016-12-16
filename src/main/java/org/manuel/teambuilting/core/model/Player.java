/**
 * 
 */
package org.manuel.teambuilting.core.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.mongodb.annotations.Immutable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Manuel Doncel Martos
 *
 */
@Immutable
@Document
@Data
@lombok.Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@JsonIgnoreProperties
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize
public class Player {
	
	@Id
	private String id;
	
	@NotNull
	@Indexed
	@Size(min=2)
	private String name;
	
	@Indexed
	@Size(min=2)
	private String nickname;
	
	@Indexed
	private Character sex;

	private String bornAddress;

	public Player() {

	}

	@PersistenceConstructor
	public Player(final String name, final String nickname, final Character sex, final String bornAddress) {
		this.name = name;
		this.nickname = nickname;
		this.sex = sex;
		this.bornAddress = bornAddress;
	}

}