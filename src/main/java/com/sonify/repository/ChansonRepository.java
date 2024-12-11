package com.sonify.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.sonify.model.Chanson;

public interface ChansonRepository extends MongoRepository<Chanson, String> {
}
