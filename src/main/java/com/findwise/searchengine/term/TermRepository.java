package com.findwise.searchengine.term;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TermRepository extends MongoRepository<Term, Long> {

    Optional<Term> findFirstByTerm(String term);
}
