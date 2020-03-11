package com.softball.Rankings;

import com.softball.Players.CaptainsList.CaptainsListDTO;

import java.util.List;

public interface IRankingService {
    void saveRanking(CaptainRankingDTO captainRankingDTO);
    List<RankingsByWeekDTO> getRankingsByWeek(Integer year);
    void createNewWeek(WeekRankDTO weekRankDTO);
    List<WeekRankDTO> getWeekRanks();
    List<CaptainsListDTO> getSubmitedRankingsByWeek(String weekRankId);
}
