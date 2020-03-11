package com.softball.Players.CaptainsList;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CaptainsListDTO {
    private String playerId;
    private String playerName;
}
