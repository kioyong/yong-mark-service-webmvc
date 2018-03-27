package com.yong.mark.repository.impl;

import com.yong.mark.vo.HotPlayerPatron;
import com.yong.mark.vo.PatronValue;
import com.yong.mark.repository.MarkRepositoryCustom;
import com.yong.mark.model.Mark;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;
import static org.springframework.data.mongodb.core.query.Criteria.where;

/**
 * @author LiangYong
 * @date 2017/11/23
 */
@AllArgsConstructor
@Repository
public class MarkRepositoryImpl implements MarkRepositoryCustom {

    private final MongoTemplate mongoTemplate;

    private final MongoOperations operations;

    @Override
    public List<Mark> findAllActivityItems() {
        return  mongoTemplate.findAll(Mark.class);
    }

    @Override
    public List<HotPlayerPatron> findAggregateByCustom(){
        Date startDate = new Date();
        Date endDate = new Date();
        List<String> list = new ArrayList<>();
        long minWinOrLoss = 0l;
        Aggregation aggregation = newAggregation(
            match(where("startDate").lte(startDate)),
            project()
                .and("patron").as("patron")
                .andExpression("substrCP(gamingDate,0,8)").as("gamingDate")
                .andExpression("add(subtract(endDate,startDate),ifNull(pauseTime,0))").as("hours")

                .and("win").as("buyIn")
                .andExpression("tt.dd").as("dd")
            ,
            group("patron"
                ,"gamingDate"
                )
            .sum("buyIn").as("buyIn")
            .sum("hours").as("hours")
            .sum("dd").as("dd"),
            project()
            .and("patron").as("patron")
                .and("gamingDate").as("gameDate")
                .and("buyIn").as("buyIn")
            .and("hours").as("hours")
            .and("dd").as("dd")
        );
        List<HotPlayerPatron> result = operations.aggregate(aggregation,"mark",HotPlayerPatron.class).getMappedResults();
        return result;
    }

    @Override
    public List<PatronValue> findAggregateBuyReduce(){
        Aggregation aggregation = newAggregation(
            group("patron")
                .sum("win").as("win"),
            project()
//                .and("patron").previousOperation()
                .and("_id").as("tableId")
//                .and("patron").previousOperation()
                .and("win").as("value")
        );
        return operations.aggregate(aggregation,"mark",PatronValue.class).getMappedResults();
    }
}
