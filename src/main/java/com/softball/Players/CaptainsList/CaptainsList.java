package com.softball.Players.CaptainsList;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "captains_list")
@Data
@Builder
public class CaptainsList {
    @Id
    public String _id;

    @Basic
    @Column(name = "playerId", nullable = false)
    public String playerId;
}
