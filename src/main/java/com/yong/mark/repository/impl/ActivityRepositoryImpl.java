package com.yong.mark.repository.impl;

import com.yong.mark.repository.ActivityRepositoryCustom;
import com.yong.mark.model.ActivitySummaryVo;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;

/**
 * @author LiangYong
 * @date 2018/02/19
 */
@Repository
@AllArgsConstructor
public class ActivityRepositoryImpl implements ActivityRepositoryCustom {

    private final MongoOperations operations;

    @Override
    public List<ActivitySummaryVo> getActivitySummary() {
        Aggregation aggregation = newAggregation(
//            project()
//            .and("_id").as("_id")
//            .andExpression("filter($activitySessions,'activitySession',$$activitySession._id == '1001')").as("activitySessions")
//            ,
//            project()
//                .and("_id").as("id")
//                .andExpression("reduce(activitySessions,0,add('$$value',sum('$$this.fills.value')))").as("fill")
//                .andExpression("reduce(activitySessions,0,add('$$value',sum('$$this.credits.value')))").as("credit")
//                .andExpression("sum(activitySessions.value)").as("opener")

            project()
                .and("_id").as("id")
                .andExpression("reduce(activitySessions,new Object[]{},concatArrays('$$value',filter('$$this.fills','fills',$$fills.status=='OPEN' || $$fills.status=='CLOSED')))").as("fills"),
            project()
                .and("id").as("id")
//                .and("activitySessions").as("activitySessions")
                .andExpression("sum(fills.value)").as("fill")

        );
        List<ActivitySummaryVo> result = operations.aggregate(aggregation,"activity",ActivitySummaryVo.class).getMappedResults();
        return result;
    }

//    project("id","opener","closer")
//                .and("property").as("tableId")
//                .andExpression("reduce(activitySessions,new Object[]{},concatArrays('$$value',filter('$$this.fills','fills','$$fills.status'=='ACKNOWLEDGED' || '$$fills.status'=='CONSUMATED')))").as("fills")
//                .andExpression("reduce(activitySessions,new Object[]{},concatArrays('$$value',filter('$$this.credits','credits','$$credits.status'=='ACKNOWLEDGED' || '$$credits.status'=='CONSUMATED')))").as("credits")
//                .andExpression("reduce(activitySessions,new Object[]{},concatArrays('$$value',$$this.dropboxUpdates))").as("dropBoxUpdates")
//            ,
}
