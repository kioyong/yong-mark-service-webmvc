package com.yong.mark.repository.impl;

import com.yong.mark.model.ActivitySummaryVo;
import com.yong.mark.repository.PatronRepositoryCustom;
import lombok.AllArgsConstructor;
import org.bson.Document;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Repository
@AllArgsConstructor
public class PatronRepositoryImpl implements PatronRepositoryCustom {

    private final MongoOperations operations;

    @Override
    public List<String> findPatronListWithoutScannedId() {
        Aggregation aggregation = newAggregation(
            match(Criteria.where("identification").is(new ArrayList<>())),
            project("_id")
        );
        List<Document> result = operations.aggregate(aggregation,"patron", Document.class ).getMappedResults();
        return result.stream().map(t -> t.get("_id", String.class)).collect(Collectors.toList());
    }

    public HashMap<String,String>test(){
        HashMap<String,String> map = new HashMap<>();
        map.put("1","1");
        return map;
    }
}
