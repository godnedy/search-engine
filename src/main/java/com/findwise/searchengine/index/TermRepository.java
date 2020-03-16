package com.findwise.searchengine.index;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TermRepository extends MongoRepository<Term, String> {

}
