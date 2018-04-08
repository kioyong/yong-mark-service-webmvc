package com.yong.mark.controller;

import com.yong.mark.model.Activity;
import com.yong.mark.model.ActivitySummaryVo;
import com.yong.mark.repository.ActivityRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/activity")
public class ActivityController {

    private final ActivityRepository activityRepository;

    @GetMapping()
    private List<Activity> findAll() {
        return activityRepository.findAll();
    }

    @GetMapping("/summary")
    private List<ActivitySummaryVo> getSummary() {
        return activityRepository.getActivitySummary();
    }
}
