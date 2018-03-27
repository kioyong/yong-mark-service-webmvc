package com.yong.mark.repository;

import com.yong.mark.model.Sequences;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SequencesRepository extends MongoRepository<Sequences,String> {
}
