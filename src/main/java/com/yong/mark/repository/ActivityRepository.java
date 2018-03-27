package com.yong.mark.repository;

import com.yong.mark.model.Activity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ActivityRepository extends MongoRepository<Activity,String> ,ActivityRepositoryCustom{
}
