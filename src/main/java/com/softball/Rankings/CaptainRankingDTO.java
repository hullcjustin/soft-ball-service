package com.softball.Rankings;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class CaptainRankingDTO {
    private final String weekRankId;
    private final String captain;
    private final String captainId;
    private final List<PlayerRankDTO> playerRankDTOList;
}
