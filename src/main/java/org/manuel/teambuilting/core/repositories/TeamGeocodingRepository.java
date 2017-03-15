package org.manuel.teambuilting.core.repositories;

import org.bson.types.ObjectId;
import org.manuel.teambuilting.core.model.TeamGeocoding;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author manuel.doncel.martos
 * @since 14-3-2017
 */
@Repository
public interface TeamGeocodingRepository extends MongoRepository<TeamGeocoding, ObjectId> {


}