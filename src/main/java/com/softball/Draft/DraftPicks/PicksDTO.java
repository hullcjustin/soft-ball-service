package com.softball.Draft.DraftPicks;

import com.softball.Players.PlayerDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PicksDTO {
    private String id;
    private Integer pick;
    private Integer round;
    private PlayerDTO playerDTO;
    private String playerId;
}
