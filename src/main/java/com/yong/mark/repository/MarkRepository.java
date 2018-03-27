package com.yong.mark.repository;

import com.yong.mark.model.Mark;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @acthor yong.a.liang
 * @date 2017/11/14
 */
@Repository
public interface MarkRepository extends MongoRepository<Mark,String>, MarkRepositoryCustom  {
    List<Mark> findAll(Sort sort);
}
