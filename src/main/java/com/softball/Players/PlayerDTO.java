package com.softball.Players;

import com.softball.Stats.StatsDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlayerDTO {
    private String id;
    private String firstName;
    private String lastName;
    private String name;
    private List<StatsDTO> statsDTOS;
}
