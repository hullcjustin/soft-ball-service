package com.softball.Stats;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends MongoRepository<Player, String>{
    Player findBy_id(String _id);
    Player findByFirstName(String firstName);
    Player findByName(String name);
}
