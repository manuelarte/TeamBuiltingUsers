package org.manuel.teambuilting.core.model.repository;

import org.manuel.teambuilting.core.model.PlayerToTeamSportDetails;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface PlayerToTeamSportDetailsRepository extends MongoRepository<PlayerToTeamSportDetails, String> {

	Set<PlayerToTeamSportDetails> findByPlayerId(String playerId);

	PlayerToTeamSportDetails findByPlayerIdAndSportLikeIgnoreCase(String playerId, String sport);

}
