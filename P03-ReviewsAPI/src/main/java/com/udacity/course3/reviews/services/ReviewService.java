package com.udacity.course3.reviews.services;

import com.udacity.course3.reviews.models.mongo.CommentNosql;
import com.udacity.course3.reviews.models.mongo.ReviewNoSql;
import com.udacity.course3.reviews.models.payload.CommentPayload;
import com.udacity.course3.reviews.models.payload.ReviewPayload;
import com.udacity.course3.reviews.models.relational.Comment;
import com.udacity.course3.reviews.models.relational.Review;
import com.udacity.course3.reviews.repository.CommentRepository;
import com.udacity.course3.reviews.repository.ReviewRepository;
import com.udacity.course3.reviews.repository.mongo.MongoReviewRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ReviewService {

    private CommentRepository commentRepository;
    private MongoReviewRepository mongoReviewRepository;
    private ReviewRepository reviewRepository;
    private ModelMapper modelMapper;

    public ReviewService(MongoReviewRepository mongoReviewRepository,
                         ReviewRepository reviewRepository,
                         ModelMapper modelMapper,
                         CommentRepository commentRepository) {
        this.mongoReviewRepository = mongoReviewRepository;
        this.reviewRepository = reviewRepository;
        this.modelMapper = modelMapper;
        this.commentRepository = commentRepository;
    }

    public Optional<Review> findById(Integer reviewId) {
        return reviewRepository.findById(reviewId);
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

    public CommentPayload saveComment(CommentPayload payload) {
        // convert to relation model - for mysql
        Comment comment = modelMapper.map(payload, Comment.class);
        CommentNosql commentNosql = modelMapper.map(payload, CommentNosql.class);
        Date date = new Date();
        comment.setCreatedAt(date);
        commentNosql.setCreatedAt(date);

        log.error("Comment " + comment.getComment() + " > reviewId " + comment.getReviewId() + "> " + comment.getCreatedAt());

//        Comment savedComment = commentRepository.save(comment);

        // get review
        Optional<ReviewNoSql> optional = mongoReviewRepository.findById(payload.getReviewId());

        if(optional.isPresent()) {
            ReviewNoSql reviewNoSql = optional.get();

            log.error("Comment " + reviewNoSql.getId() + " > reviewId "); // + reviewNoSql.getReview() + "> " + reviewNoSql.getCreatedAt() + "> " + reviewNoSql.getComments().size());
            List<CommentNosql> comments = reviewNoSql.getComments();

            if(comments != null) {
                comments.add(commentNosql);
            } else {
                comments = new ArrayList<>();
                comments.add(commentNosql);
                reviewNoSql.setComments(comments);
            }

            mongoReviewRepository.save(reviewNoSql);
        }

        return modelMapper.map(payload, CommentPayload.class);
    }
}
