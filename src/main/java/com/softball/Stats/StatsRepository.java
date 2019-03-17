package com.softball.Stats;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface StatsRepository  extends MongoRepository<Stats, String>{

    public List<Stats> findByPlayerId(String id);
    public Stats findByYear(Integer year);
    public Stats findByPlayerIdAndYear(String id, Integer year);
}
