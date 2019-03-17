package com.softball.Stats;

import java.util.List;
import java.util.stream.Collectors;

public class StatsDTOFactory {

    public static StatsDTO create(Stats stats){
        return new StatsDTO(
                stats.playerId,
                stats.year,
                stats.plateAppearences,
                stats.atBats,
                stats.runs,
                stats.hits,
                stats.firstBase,
                stats.secondBase,
                stats.thirdBase,
                stats.homeRuns,
                stats.rbis,
                stats.walks,
                stats.battingPercentage,
                stats.average,
                stats.slugingPercentag,
                stats.playersInScoringPosition,
                stats.captain,
                stats.defensivePosition
        );
    }

    public static List<StatsDTO> create(List<Stats> statsList){
        return statsList
                .stream()
                .map(stats ->{
                    return new StatsDTO(
                            stats.playerId,
                            stats.year,
                            stats.plateAppearences,
                            stats.atBats,
                            stats.runs,
                            stats.hits,
                            stats.firstBase,
                            stats.secondBase,
                            stats.thirdBase,
                            stats.homeRuns,
                            stats.rbis,
                            stats.walks,
                            stats.battingPercentage,
                            stats.average,
                            stats.slugingPercentag,
                            stats.playersInScoringPosition,
                            stats.captain,
                            stats.defensivePosition
                    );
                }).collect(Collectors.toList());
    }
}
