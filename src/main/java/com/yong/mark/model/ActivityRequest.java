package com.yong.mark.model;

import lombok.Data;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

@Data
public class ActivityRequest {

    private List<String> tableIds;
    private Date startDate;
    private Date endDate;
    private ZonedDateTime fromDate;
    private ZonedDateTime toDate;

}
