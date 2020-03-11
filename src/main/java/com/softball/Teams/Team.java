package com.softball.Teams;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import javax.persistence.*;

@Entity
@Table(name = "Team")
@Data
@Builder
public class Team {
    @Id
    public String id;

    @Basic
    @Column(name = "player_id", nullable = false)
    public String playerId;

    @Basic
    @Column(name = "size", nullable = false)
    public Integer size;

    @Basic
    @Column(name = "name", nullable = false)
    public String name;

    @Basic
    @Column(name = "year", nullable = false)
    public Integer year;

    @Basic
    @Column(name = "league", nullable = false)
    public String league;

    @Tolerate
    public Team(){
    }
}
