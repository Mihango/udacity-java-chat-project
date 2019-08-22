package com.udacity.course3.reviews.services;

import com.udacity.course3.reviews.models.mongo.ReviewNoSql;
import com.udacity.course3.reviews.models.payload.ReviewPayload;
import com.udacity.course3.reviews.models.relational.Review;
import com.udacity.course3.reviews.repository.ReviewRepository;
import com.udacity.course3.reviews.repository.mongo.MongoReviewRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

@Service
public class ReviewService {

    private MongoReviewRepository mongoReviewRepository;
    private ReviewRepository reviewRepository;
    private ModelMapper modelMapper;

    public ReviewService(MongoReviewRepository mongoReviewRepository, ReviewRepository reviewRepository, ModelMapper modelMapper) {
        this.mongoReviewRepository = mongoReviewRepository;
        this.reviewRepository = reviewRepository;
        this.modelMapper = modelMapper;
    }

    public ReviewPayload saveReview(ReviewPayload payload) {
        // map payload to specific models
        // todo set date
        Review review = modelMapper.map(payload, Review.class);
        ReviewNoSql reviewNoSql = modelMapper.map(payload, ReviewNoSql.class);
        Date date = new Date();
        review.setCreatedAt(date);
        reviewNoSql.setCreatedAt(date);

        // save review in mysql
        Review savedReview = reviewRepository.save(review);

        // set no sql review id from saved one
        reviewNoSql.setId(savedReview.getId());

        // save no sql review in mongo
        ReviewNoSql savedReviewNoSql = mongoReviewRepository.save(reviewNoSql);

        return modelMapper.map(savedReviewNoSql, ReviewPayload.class);
    }

    public List<ReviewPayload> findAll() {
        List<ReviewNoSql> reviews = mongoReviewRepository.findAll();
        Type listType = new TypeToken<List<ReviewPayload>>() {}.getType();
        return modelMapper.map(reviews, listType);
    }
}
