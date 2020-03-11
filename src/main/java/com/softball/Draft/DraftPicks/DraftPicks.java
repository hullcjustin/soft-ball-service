package com.softball.Draft.DraftPicks;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import javax.persistence.*;

@Entity
@Table(name = "draft_picks")
@Data
@Builder
public class DraftPicks {
    @Id
    public String _id;

    @Basic
    @Column(name = "playerId", nullable = false)
    public String playerId;

    @Basic
    @Column(name = "draftId", nullable = false)
    public String draftId;

    @Basic
    @Column(name = "pick", nullable = false)
    public Integer pick;

    @Basic
    @Column(name = "round", nullable = false)
    public Integer round;

    @Tolerate
    public DraftPicks() {
    }
}
