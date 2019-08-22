package com.udacity.course3.reviews.controller;

import com.udacity.course3.reviews.models.payload.CommentPayload;
import com.udacity.course3.reviews.models.relational.Comment;
import com.udacity.course3.reviews.models.relational.Review;
import com.udacity.course3.reviews.repository.CommentRepository;
import com.udacity.course3.reviews.repository.ReviewRepository;
import com.udacity.course3.reviews.services.ReviewService;
import javassist.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Spring REST controller for working with comment entity.
 */
@RestController
@RequestMapping("/comments")
public class CommentsController {

    //  Wire needed JPA repositories here
    private ReviewRepository reviewRepository;
    private CommentRepository commentRepository;
    private ReviewService reviewService;

    public CommentsController(ReviewRepository reviewRepository, CommentRepository commentRepository, ReviewService reviewService) {
        this.reviewRepository = reviewRepository;
        this.commentRepository = commentRepository;
        this.reviewService = reviewService;
    }

    /**
     * Creates a comment for a review.
     *
     * 1. Add argument for comment entity. Use {@link RequestBody} annotation.
     * 2. Check for existence of review.
     * 3. If review not found, return NOT_FOUND.
     * 4. If found, save comment.
     *
     * @param reviewId The id of the review.
     */
    @RequestMapping(value = "/reviews/{reviewId}", method = RequestMethod.POST)
    public ResponseEntity<?> createCommentForReview(@PathVariable("reviewId") Integer reviewId, @Valid @RequestBody CommentPayload comment) throws Exception{
        Optional<Review> optional = reviewService.findById(reviewId);
        if(optional.isPresent()) {
            CommentPayload savedComment = reviewService.saveComment(comment);
            return ResponseEntity.ok(savedComment);
        }

        // todo return notFound
        return ResponseEntity.notFound().build();
    }

    /**
     * List comments for a review.
     *
     * 2. Check for existence of review.
     * 3. If review not found, return NOT_FOUND.
     * 4. If found, return list of comments.
     *
     * @param reviewId The id of the review.
     */
    @RequestMapping(value = "/reviews/{reviewId}", method = RequestMethod.GET)
    public List<?> listCommentsForReview(@PathVariable("reviewId") Integer reviewId) {
        return commentRepository.findByReviewId(reviewId);
    }
}
