package com.softball.Stats;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.bson.types.ObjectId;

@Data
@AllArgsConstructor
@Builder
public class StatsDTO {

    private String playerId;
    private Integer year;
    private Integer plateAppearances;
    private Integer atBats;
    private Integer runs;
    private Integer hits;
    private Integer firstBase;
    private Integer secondBase;
    private Integer thirdBase;
    private Integer homeRuns;
    private Integer rbis;
    private Integer walks;
    private double battingPercentage;
    private double average;
    private double slugingPercentage;
    private double playersInScoringPosition;
    private String captain;
    private String defensivePosition;
}
