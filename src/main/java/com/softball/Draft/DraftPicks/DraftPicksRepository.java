package com.softball.Draft.DraftPicks;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface DraftPicksRepository extends MongoRepository<DraftPicks, String> {
    List<DraftPicks> findByDraftId(String draftId);
    DraftPicks findByDraftIdAndPlayerId(String draftId, String playerId);
}
