/**
 * 
 */
package org.manuel.teambuilting.core.model;

import com.mongodb.annotations.Immutable;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

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
public class Team {

	@Id
	private String id;

	@NotNull
	@Indexed
	private final String name;
	private final String location;
	@NotNull
	@Indexed
	private final String sport;

	private final String bio;
	private final Date fromDate;
	private final Date toDate;

	@PersistenceConstructor
	public Team(final String name, final String location, final String sport, final String bio, final Date fromDate, final Date toDate) {
		this.name = name;
		this.location = location;
		this.sport = sport;
		this.bio = bio;
		this.fromDate = fromDate;
		this.toDate = toDate;
	}

}
