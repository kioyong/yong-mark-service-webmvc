package com.yong.mark.repository;

import com.yong.mark.model.Activity;
import com.yong.mark.model.ActivitySummaryVo;

import java.util.List;

public interface ActivityRepositoryCustom {
    List<ActivitySummaryVo> getActivitySummary();
}
