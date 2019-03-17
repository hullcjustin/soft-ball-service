package com.softball.Stats;

import lombok.experimental.Tolerate;
import org.bson.types.ObjectId;

import javax.persistence.*;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "player")
@Data
@Builder
public class Player {

    @Id
    public String _id;

    @Basic
    @Column(name = "firstName", nullable = false)
    public String firstName;

    @Basic
    @Column(name = "lastName", nullable = false)
    public String lastName;

    @Basic
    @Column(name = "name", nullable = false)
    public String name;



    @Tolerate
    public Player() {
    }
}
