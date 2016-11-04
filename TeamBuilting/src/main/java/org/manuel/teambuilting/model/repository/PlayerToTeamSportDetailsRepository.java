package org.manuel.teambuilting.model.repository;

import org.manuel.teambuilting.model.PlayerToTeamSportDetails;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FootballPositionsRepository extends MongoRepository<PlayerToTeamSportDetails, String> {

	PlayerToTeamSportDetails findByPlayerId(String playerId);

}
