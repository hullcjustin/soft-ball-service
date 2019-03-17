package com.softball.Stats;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.bson.types.ObjectId;

@Data
@AllArgsConstructor
@Builder
public class StatsDTO {

    private final String playerId;
    private final Integer year;
    private final Integer plateAppearances;
    private final Integer atBats;
    private final Integer runs;
    private final Integer hits;
    private final Integer firstBase;
    private final Integer secondBase;
    private final Integer thirdBase;
    private final Integer homeRuns;
    private final Integer rbis;
    private final Integer walks;
    private final double battingPercentage;
    private final double average;
    private final double slugingPercentage;
    private final double playersInScoringPosition;
    private final String captain;
    private final String defensivePosition;
}
