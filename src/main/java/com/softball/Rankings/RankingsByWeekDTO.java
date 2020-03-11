package com.softball.Rankings;

import com.softball.Rankings.PlayerRankDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class RankingsByWeekDTO {
    private final String id;
    private final Integer week;
    private final String position;
    private final String status;
    private final List<PlayerRankDTO> playerRankDTOList;
}
