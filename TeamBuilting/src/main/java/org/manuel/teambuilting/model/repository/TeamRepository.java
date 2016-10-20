package org.manuel.teambuilting.model.repository;

import java.util.Collection;

import org.manuel.teambuilting.model.Team;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends MongoRepository<Team, String> {

    Collection<Team> findByName(String name);
}
