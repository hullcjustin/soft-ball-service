package com.softball.Rankings;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "WeekRank")
@Data
@Builder
public class WeekRank {
    @Id
    public String id;

    @Basic
    @Column(name = "size", nullable = false)
    public Integer size;

    @Basic
    @Column(name = "year", nullable = false)
    public Integer year;

    @Basic
    @Column(name = "week", nullable = false)
    public Integer week;

    @Basic
    @Column(name = "position", nullable = false)
    public String position;

    @Basic
    @Column(name = "rankStart", nullable = false)
    public Date rankStart;

    @Basic
    @Column(name = "player_id", nullable = false)
    public Date rankEnd;

    @Basic
    @Column(name = "status", nullable = false)
    public String status;
}
