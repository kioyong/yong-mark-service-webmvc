package com.yong.mark.repository;

import com.yong.mark.model.Table;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TableRepository extends MongoRepository<Table,String> {
//    Table findFirstByTableIdByDateDesc(String tableId);
    Table findFirstByTableIdAndStatusNotNullOrderByDateDesc(String tableId);
}
