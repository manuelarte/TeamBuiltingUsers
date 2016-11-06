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

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Manuel Doncel Martos
 *
 */
@Immutable
@Document
@Getter
@lombok.Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Player {
	
	@Id
	private String id;
	
	@NotNull
	@Indexed
	private final String name;
	
	@Indexed
	private final String nickname;
	
	@Indexed
	private final Character sex;

	private final String bornAddress;

	@PersistenceConstructor
	public Player(final String name, final String nickname, final Character sex, final String bornAddress) {
		this.name = name;
		this.nickname = nickname;
		this.sex = sex;
		this.bornAddress = bornAddress;
	}
	
	public static class Builder {
	}

}