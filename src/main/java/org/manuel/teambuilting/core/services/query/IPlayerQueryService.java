package org.manuel.teambuilting.core.services.query;

import java.util.Optional;

import org.manuel.teambuilting.core.model.Player;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author manuel.doncel.martos
 * @since 14-3-2017
 */
public interface IPlayerQueryService {

	Optional<Player> findOne(String playerId);

	Page<Player> findPlayerByName(final Pageable pageable, final String name);

}
