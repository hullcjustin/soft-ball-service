package com.softball.Rankings;


import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Ranking")
@Data
@Builder
public class Ranking{
    @Id
    public String id;

    @Basic
    @Column(name = "player_id", nullable = false)
    public String playerId;

    @Basic
    @Column(name = "weekRankId", nullable = false)
    public String weekRankId;

    @Basic
    @Column(name = "captainId", nullable = false)
    public String captainId;

    @Basic
    @Column(name = "Captain", nullable = false)
    public String Captain;

    @Basic
    @Column(name = "date", nullable = false)
    public Date date;

    @Basic
    @Column(name = "rank", nullable = false)
    public Integer rank;

    @Basic
    @Column(name = "points", nullable = false)
    public Integer points;
}
