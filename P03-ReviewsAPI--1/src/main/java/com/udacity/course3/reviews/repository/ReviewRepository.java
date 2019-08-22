package com.udacity.course3.reviews.repository;

import com.udacity.course3.reviews.models.Review;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends CrudRepository<Review, Integer> {
    List<Review> findAll();
    List<Review> findAllByProductId(Integer productId);
}
