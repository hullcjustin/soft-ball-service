package com.softball.Rankings;

public class WeekRankDTOFactory {

    public static WeekRankDTO create(WeekRank weekRank){
        return new WeekRankDTO(
                weekRank.id,
                weekRank.size,
                weekRank.status,
                weekRank.week,
                weekRank.position,
                weekRank.rankStart,
                weekRank.rankEnd
        );
    }
}
