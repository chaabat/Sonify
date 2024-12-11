package com.sonify.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.sonify.model.User;

public interface UserRepository extends MongoRepository<User, String> {

}
