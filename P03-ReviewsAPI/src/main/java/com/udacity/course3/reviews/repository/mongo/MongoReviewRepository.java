package com.udacity.course3.reviews.repository.mongo;

import com.udacity.course3.reviews.models.mongo.ReviewNoSql;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MongoReviewRepository extends MongoRepository<ReviewNoSql, Integer> {
}
