package com.softball.Players.CaptainsList;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CaptainsListRepository extends MongoRepository<CaptainsList, String> {
}
