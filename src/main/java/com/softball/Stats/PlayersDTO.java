package com.softball.Stats;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.bson.types.ObjectId;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class PlayersDTO {

    private final String id;
    private final String firstName;
    private final String lastName;
    private final String name;
    private final List<StatsDTO> statsDTOS;

}
