package com.softball.Stats;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;
import org.bson.types.ObjectId;


import javax.persistence.*;


@Entity
@Table(name = "Stats")
@Data
@Builder
public class Stats {
    @Id
    public String id;

    @Basic
    @Column(name = "player_id", nullable = false)
    public String playerId;

    @Basic
    @Column(name = "year", nullable = false)
    public Integer year;

    @Basic
    @Column(name = "plateAppearences", nullable = false)
    public Integer plateAppearences;

    @Basic
    @Column(name = "atBats", nullable = false)
    public Integer atBats;

    @Basic
    @Column(name = "runs", nullable = false)
    public Integer runs;

    @Basic
    @Column(name = "hits", nullable = false)
    public Integer hits;

    @Basic
    @Column(name = "firstBase", nullable = false)
    public Integer firstBase;

    @Basic
    @Column(name = "secondBase", nullable = false)
    public Integer secondBase;

    @Basic
    @Column(name = "thirdBase", nullable = false)
    public Integer thirdBase;

    @Basic
    @Column(name = "homeRuns", nullable = false)
    public Integer homeRuns;

    @Basic
    @Column(name = "rbis", nullable = false)
    public Integer rbis;

    @Basic
    @Column(name = "walks", nullable = false)
    public Integer walks;

    @Basic
    @Column(name = "battingPercentage", nullable = false)
    public double battingPercentage;

    @Basic
    @Column(name = "average", nullable = false)
    public double average;

    @Basic
    @Column(name = "slugingPercentag", nullable = false)
    public double slugingPercentag;

    @Basic
    @Column(name = "playersInScoringPosition", nullable = false)
    public double playersInScoringPosition;

    @Basic
    @Column(name = "captain", nullable = false)
    public String captain;

    @Basic
    @Column(name = "defensivePosition", nullable = false)
    public String defensivePosition;


    @Tolerate
    public Stats() {
    }
}
