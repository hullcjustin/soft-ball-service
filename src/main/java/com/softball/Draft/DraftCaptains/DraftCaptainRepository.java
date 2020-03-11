package com.softball.Draft.DraftCaptains;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DraftCaptainRepository extends MongoRepository<DraftCaptain, String> {
    List<DraftCaptain> findByDraftId(String draftId);
}
