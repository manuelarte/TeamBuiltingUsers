package org.manuel.teambuilting.model.repository;

import org.manuel.teambuilting.model.PlayerToTeamSportDetails;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerToTeamSportDetailsRepository extends MongoRepository<PlayerToTeamSportDetails, String> {

	PlayerToTeamSportDetails findByPlayerIdAndSportLikeIgnoreCase(String playerId, String sport);

}
