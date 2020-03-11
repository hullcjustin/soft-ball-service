package com.softball.Draft;
import com.softball.Draft.DraftCaptains.DraftCaptainDTO;
import com.softball.Draft.DraftPicks.PicksDTO;
import com.softball.Players.PlayerDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DraftDTO {
    private String id;
    private Integer year;
    private Integer rounds;
    private String type;
    private Integer numberOfCaptains;
    private List<DraftCaptainDTO> captains;
    private List<PicksDTO> picks;
}
