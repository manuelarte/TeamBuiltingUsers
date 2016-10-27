/**
 * 
 */
package org.manuel.teambuilting.model.repository;

import java.util.Set;

import org.manuel.teambuilting.model.Player;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Manuel Doncel Martos
 *
 */
@Repository
public interface PlayerRepository extends MongoRepository<Player, String> {

	Set<Player> findByNameLike(final String name);

}
