package org.manuel.teambuilting.core.model.repository;

import java.util.Set;

import org.manuel.teambuilting.core.model.Team;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author Manuel Doncel Martos
 *
 */
@Repository
public interface TeamRepository extends MongoRepository<Team, String> {

	Set<Team> findBySportLikeIgnoreCase(String sport);

}
