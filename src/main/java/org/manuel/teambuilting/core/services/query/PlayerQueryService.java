package org.manuel.teambuilting.core.services.query;

import org.manuel.teambuilting.core.model.Player;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author manuel.doncel.martos
 * @since 14-3-2017
 */
public interface PlayerQueryService extends BaseQueryService<Player, String> {

	Page<Player> findPlayerByName(final Pageable pageable, final String name);

}
