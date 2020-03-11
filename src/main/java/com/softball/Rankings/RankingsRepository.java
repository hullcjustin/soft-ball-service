package com.softball.Rankings;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RankingsRepository extends MongoRepository<Ranking, Integer>{
    public List<Ranking> findByWeekRankId(String weekRankId);
    public List<Ranking> findByWeekRankIdAndPlayerId(String weekRankId, String PlayerId);
}
