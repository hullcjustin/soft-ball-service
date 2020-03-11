package com.softball.Draft.DraftCaptains;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import javax.persistence.*;

@Entity
@Table(name = "draft_captains")
@Data
@Builder
public class DraftCaptain {
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

    @Tolerate
    public DraftCaptain() {
    }
}
