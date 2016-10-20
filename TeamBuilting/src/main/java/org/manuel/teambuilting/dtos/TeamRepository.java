package org.manuel.teambuilting.dtos;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends CrudRepository<Team, DTOId> {

    Collection<Team> findByName(String name);
}
