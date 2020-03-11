package com.softball.Teams;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class TeamsDTO {
    private final String id;
    private final String playerId;
    private final Integer size;
    private final String name;
    private final Integer year;
    private final String league;

}
