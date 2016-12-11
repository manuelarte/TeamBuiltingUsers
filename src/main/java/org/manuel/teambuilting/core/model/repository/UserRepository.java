package org.manuel.teambuilting.core.model.repository;

import org.manuel.teambuilting.core.model.UserData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Manuel on 11/12/2016.
 */
@Repository
public interface UserRepository extends MongoRepository<UserData, String>  {

    UserData findByUserId(String userId);

}
