/**
 * 
 */
package org.manuel.teambuilting.model.repository;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.manuel.teambuilting.model.TeamHist;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Manuel Doncel Martos
 *
 */
@Repository
public interface TeamHistRepository extends MongoRepository<TeamHist, String> {

	Set<TeamHist> findByNameLikeIgnoreCase(final String name);

	TeamHist findByFromDateBeforeAndToDateAfterAndTeamId(final Date fromDate, final Date toDate, final String teamId);

	List<TeamHist> findByTeamIdOrderByFromDateDesc(String teamId);

}