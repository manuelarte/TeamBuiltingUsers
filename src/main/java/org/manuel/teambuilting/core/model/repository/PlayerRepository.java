/**
 * 
 */
package org.manuel.teambuilting.core.model.repository;

import java.util.Set;

import org.manuel.teambuilting.core.model.Player;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Manuel Doncel Martos
 *
 */
@Repository
public interface PlayerRepository extends MongoRepository<Player, String> {

	Set<Player> findByNameLikeIgnoreCase(final String name);

}
