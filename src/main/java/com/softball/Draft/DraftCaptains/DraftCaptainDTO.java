package com.softball.Draft.DraftCaptains;

import com.softball.Players.PlayerDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DraftCaptainDTO {
    private PlayerDTO captain;
    private String playerId;
    private Integer pick;
}
