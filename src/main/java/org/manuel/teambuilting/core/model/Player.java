/**
 * 
 */
package org.manuel.teambuilting.core.model;

import com.mongodb.annotations.Immutable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
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
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Player {
	
	@Id
	private String id;
	
	@NotNull
	@Indexed
	@Size(min=2)
	private final String name;
	
	@Indexed
	@Size(min=2)
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