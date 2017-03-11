/**
 * 
 */
package org.manuel.teambuilting.core.repositories;

import org.manuel.teambuilting.core.model.Player;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Manuel Doncel Martos
 *
 */
@Repository
public interface PlayerRepository extends MongoRepository<Player, String> {

	Page<Player> findByNameLikeIgnoreCase(Pageable pageable, String name);

}
