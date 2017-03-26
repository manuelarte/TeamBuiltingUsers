/**
 * 
 */
package org.manuel.teambuilting.core.model;

import com.mongodb.annotations.Immutable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.manuel.teambuilting.core.validations.TeamExists;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author Manuel Doncel Martos
 *
 */
@Immutable
@Document
@Data
@lombok.Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PlayerToTeam implements TimeSlice {

	@Id
	private String id;

	@NotNull
	@Indexed
	private String playerId;

	@NotNull
	@Indexed
	@TeamExists
	private String teamId;

	@NotNull
	private Date fromDate;

	private Date toDate;
	
	@AssertTrue
	private boolean startDateBeforeEndDate() {
		return toDate == null || toDate.getTime() > fromDate.getTime();
	}

	@PersistenceConstructor
	public PlayerToTeam(final String playerId, final String teamId, final Date fromDate, final Date toDate) {
		this.playerId = playerId;
		this.teamId = teamId;
		this.fromDate = fromDate;
		this.toDate = toDate;
	}
	
}
