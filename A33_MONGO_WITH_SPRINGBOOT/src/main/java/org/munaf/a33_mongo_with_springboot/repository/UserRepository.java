package org.munaf.a33_mongo_with_springboot.repository;

import org.munaf.a33_mongo_with_springboot.entity.UserMaster;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<UserMaster, String> {
    boolean existsByEmail(String email);

    List<UserMaster> findByAddressCity(String city);

    List<UserMaster> findByAddressCityAndAddressState(String city, String state);

}
