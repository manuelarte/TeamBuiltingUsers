package org.manuel.teambuilting.core.model.repository;

import java.util.Set;

import org.manuel.teambuilting.core.model.PlayerToTeamSportDetails;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerToTeamSportDetailsRepository extends MongoRepository<PlayerToTeamSportDetails, String> {

	Set<PlayerToTeamSportDetails> findByPlayerId(String playerId);

	PlayerToTeamSportDetails findByPlayerIdAndSportIgnoringCase(String playerId, String sport);

}
