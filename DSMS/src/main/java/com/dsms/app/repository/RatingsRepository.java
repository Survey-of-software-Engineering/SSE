package com.dsms.app.repository;

import com.dsms.app.entity.Ratings;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingsRepository extends MongoRepository<Ratings, String> {

    Ratings getRatingsById(String ratingId);

    Ratings getRatingsByUserIdAndItemId(String userId, String itemId);
}