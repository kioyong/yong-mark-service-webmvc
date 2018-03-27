package com.yong.mark.repository;

import com.yong.mark.model.ExceptionLog;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Map;

/**
 * @acthor yong.a.liang
 * @date 2018/01/11
 */
public interface ExceptionLogRepository extends MongoRepository<ExceptionLog, String> {
    ExceptionLog findByFirstDetail(Map<String, String> firstDetail);
}
