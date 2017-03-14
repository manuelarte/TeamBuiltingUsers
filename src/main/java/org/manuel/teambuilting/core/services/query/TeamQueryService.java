package org.manuel.teambuilting.core.services.query;

import org.manuel.teambuilting.core.model.Team;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author manuel.doncel.martos
 * @since 14-3-2017
 */
public interface TeamQueryService extends BaseQueryService<Team, String> {


	Page<Team> findTeamBy(Pageable pageable, String sport, String name);

}
