package org.manuel.teambuilting.core.repositories;

import java.util.Date;
import java.util.Set;

import org.manuel.teambuilting.core.model.Team;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Manuel Doncel Martos
 */
@Repository
public interface TeamRepository extends MongoRepository<Team, String> {

	Set<Team> findBySportLikeIgnoreCase(String sport);

	Set<Team> findByNameLikeIgnoreCase(final String name);

	Team findByFromDateBeforeAndToDateAfter(final Date fromDate, final Date toDate);

}
