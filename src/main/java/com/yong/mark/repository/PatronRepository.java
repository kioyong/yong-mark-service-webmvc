package com.yong.mark.repository;

import com.yong.mark.model.Patron;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PatronRepository extends MongoRepository<Patron,String> {
}
