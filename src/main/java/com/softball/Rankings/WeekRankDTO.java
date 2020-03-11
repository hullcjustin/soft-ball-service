package com.softball.Rankings;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
public class WeekRankDTO {
    private final String id;
    private final Integer size;
    private final String Status;
    private final Integer week;
    private final String position;
    private final Date StartDate;
    private final Date EndDate;
}
