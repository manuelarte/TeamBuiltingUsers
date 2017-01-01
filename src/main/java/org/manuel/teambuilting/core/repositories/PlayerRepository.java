/**
 * 
 */
package org.manuel.teambuilting.core.repositories;

import org.manuel.teambuilting.core.model.Player;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * @author Manuel Doncel Martos
 *
 */
@Repository
public interface PlayerRepository extends MongoRepository<Player, String> {

	Set<Player> findByNameLikeIgnoreCase(final String name);

}
