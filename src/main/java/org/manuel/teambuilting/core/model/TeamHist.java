/**
 * 
 */
package org.manuel.teambuilting.core.model;

import java.util.Date;

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
@lombok.Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class TeamHist {

	@Id
	private String id;
	@NotNull
	@Indexed
	private final String teamId;
	@Indexed
	private final String name;
	private final String location;
	private final Date fromDate;
	private final Date toDate;
	
	@PersistenceConstructor
	public TeamHist(final String teamId, final String name, final String location, final Date fromDate, final Date toDate) {
		this.teamId = teamId;
		this.name = name;
		this.location = location;
		this.fromDate = fromDate;
		this.toDate = toDate;
	}

}
