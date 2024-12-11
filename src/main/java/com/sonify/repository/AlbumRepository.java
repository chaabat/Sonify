package com.sonify.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.sonify.model.Album;

public interface AlbumRepository extends MongoRepository<Album, String> {
}
