package org.manuel.teambuilting.core.model.repository;

import org.manuel.teambuilting.core.model.PlayerToTeamSportDetails;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerToTeamSportDetailsRepository extends MongoRepository<PlayerToTeamSportDetails, String> {

	PlayerToTeamSportDetails findByPlayerIdAndSportLikeIgnoreCase(String playerId, String sport);

}
