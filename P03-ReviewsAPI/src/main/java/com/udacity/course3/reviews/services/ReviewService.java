package com.udacity.course3.reviews.services;

import com.udacity.course3.reviews.repository.ReviewRepository;
import com.udacity.course3.reviews.repository.mongo.MongoReviewRepository;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    private MongoReviewRepository mongoReviewRepository;
    private ReviewRepository reviewRepository;

    public ReviewService(MongoReviewRepository mongoReviewRepository, ReviewRepository reviewRepository) {
        this.mongoReviewRepository = mongoReviewRepository;
        this.reviewRepository = reviewRepository;
    }
}
