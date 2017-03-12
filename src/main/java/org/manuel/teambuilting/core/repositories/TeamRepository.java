package org.manuel.teambuilting.core.repositories;

import org.manuel.teambuilting.core.model.Team;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Manuel Doncel Martos
 */
@Repository
public interface TeamRepository extends MongoRepository<Team, String> {

	Page<Team> findBySportLikeIgnoreCaseAndNameLikeIgnoreCase(Pageable pageable, String sport, String name);

}
