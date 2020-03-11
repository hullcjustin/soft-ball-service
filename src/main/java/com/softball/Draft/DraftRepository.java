package com.softball.Draft;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface DraftRepository extends MongoRepository<Draft, String> {
    Draft findDistinctFirstBy_id(String id);
}
