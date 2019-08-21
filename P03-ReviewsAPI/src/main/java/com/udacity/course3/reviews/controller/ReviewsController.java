package com.udacity.course3.reviews.controller;

import com.udacity.course3.reviews.models.payload.ReviewPayload;
import com.udacity.course3.reviews.models.relational.Review;
import com.udacity.course3.reviews.repository.ProductRepository;
import com.udacity.course3.reviews.repository.ReviewRepository;
import com.udacity.course3.reviews.services.ReviewService;
import javassist.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

/**
 * Spring REST controller for working with review entity.
 */
@RestController
public class ReviewsController {

    // Wire JPA repositories here
    private ReviewService reviewService;
    private ProductRepository productRepository;

    public ReviewsController(ReviewService reviewService, ProductRepository productRepository) {
        this.reviewService = reviewService;
        this.productRepository = productRepository;
    }

    /**
     * Creates a review for a product.
     * <p>
     * 1. Add argument for review entity. Use {@link RequestBody} annotation.
     * 2. Check for existence of product.
     * 3. If product not found, return NOT_FOUND.
     * 4. If found, save review.
     *
     * @param productId The id of the product.
     * @return The created review or 404 if product id is not found.
     */
    @RequestMapping(value = "/reviews/products/{productId}", method = RequestMethod.POST)
    public ResponseEntity<?> createReviewForProduct(@PathVariable("productId") Integer productId, @Valid @RequestBody ReviewPayload review) throws Exception {
        Review savedReview;
        if(productRepository.findById(productId).isPresent()) {
            review.setProductId(productId);
            ReviewPayload payload = reviewService.saveReview(review);
            return ResponseEntity.ok(payload);
        }

        throw new NotFoundException("Product with id " + productId + "not found");
    }

    /**
     * Lists reviews by product.
     *
     * @param productId The id of the product.
     * @return The list of reviews.
     */
    @RequestMapping(value = "/reviews/products/{productId}", method = RequestMethod.GET)
    public ResponseEntity<List<?>> listReviewsForProduct(@PathVariable("productId") Integer productId) {
        return ResponseEntity.ok(null); //reviewService.findAll());
    }
}
