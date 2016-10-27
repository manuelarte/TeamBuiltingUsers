package org.manuel.teambuilting.model.repository;

import org.manuel.teambuilting.model.Team;
import org.manuel.teambuilting.model.TeamId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author Manuel Doncel Martos
 *
 */
@Repository
public interface TeamRepository extends MongoRepository<Team, TeamId> {

}
