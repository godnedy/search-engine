package com.findwise.searchengine.term;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TermRepository extends MongoRepository<TermDocument, String> {

    Optional<TermDocument> findFirstByTermValue(String termValue);
}
