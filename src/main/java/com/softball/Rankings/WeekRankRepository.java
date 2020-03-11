package com.softball.Rankings;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WeekRankRepository extends MongoRepository<WeekRank, String> {
    public List<WeekRank> findByYear(Integer year);
    public WeekRank findByid(String id);
}
