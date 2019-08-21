package com.udacity.course3.reviews.repository;

import com.udacity.course3.reviews.models.relational.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Integer> {
    List<Comment> findAll();
    List<Comment> findByReviewId(Integer reviewId);
}
