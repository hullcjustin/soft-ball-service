package com.softball.Draft;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "draft")
@Data
@Builder
public class Draft {
    @Id
    public String _id;

    @Basic
    @Column(name = "year", nullable = false)
    public Integer year;

    @Basic
    @Column(name = "rounds", nullable = false)
    public Integer rounds;

    @Basic
    @Column(name = "type", nullable = false)
    public String type;

    @Basic
    @Column(name = "number_of_captains", nullable = false)
    public Integer numberOfCaptains;

    @Basic
    @Column(name = "date_created", nullable = false)
    public Date dateCreated;

    @Tolerate
    public Draft() {
    }
}
