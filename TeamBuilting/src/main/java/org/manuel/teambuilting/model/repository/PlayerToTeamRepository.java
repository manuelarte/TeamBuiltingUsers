/**
 * 
 */
package org.manuel.teambuilting.model.repository;

import java.time.LocalDate;
import java.util.Collection;

import org.manuel.teambuilting.model.PlayerToTeam;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Manuel Doncel Martos
 *
 */
@Repository
public interface PlayerToTeamRepository extends MongoRepository<PlayerToTeam, String> {

	/**
	 * Look for all the players that have played in the team whose id is teamId
	 * and they stopped played after the parameter end date
	 * 
	 * @param date
	 * @param teamId
	 * @return
	 */
	Collection<PlayerToTeam> findByEndDateAfterOrEndDateIsNullAndTeamId(LocalDate date, String teamId);

	Collection<PlayerToTeam> findByPlayerId(String playerId);

}
