package com.softball.Rankings;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class PlayerRankDTO {
    private final String playerId;
    private final String playerName;
    private final Integer rank;
    private final Integer points;
}
