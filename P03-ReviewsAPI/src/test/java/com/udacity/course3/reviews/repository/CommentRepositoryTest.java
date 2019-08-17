package com.udacity.course3.reviews.repository;

import com.udacity.course3.reviews.models.Comment;
import com.udacity.course3.reviews.models.Product;
import com.udacity.course3.reviews.models.Review;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CommentRepositoryTest {

    @Autowired
    ReviewRepository reviewRepository;
    @Autowired ProductRepository productRepository;
    @Autowired CommentRepository commentRepository;

    @Test
    public void injectedNotNull() {
        assertThat(reviewRepository).isNotNull();
        assertThat(productRepository).isNotNull();
        assertThat(commentRepository).isNotNull();
    }

    @Test
    public void findComments() {
        Product product = new Product();
        product.setName("Samsung A30");
        product.setPrice(2000.0);
        product.setDescription("Phone");
        product.setId(20);

        Product p = productRepository.save(product);

        Review review = new Review();
        review.setProductId(20);
        review.setReview("Not bad");
        review.setCreatedAt(new Date());
        review.setProductId(p.getId());

        Review r = reviewRepository.save(review);

        Comment comment = new Comment();
        comment.setComment("I like it");
        comment.setCreatedAt(new Date());
        comment.setReviewId(r.getId());

        commentRepository.save(comment);

        assertThat(reviewRepository.findAll()).isNotNull();
        assertThat(reviewRepository.findAll().size()).isEqualTo(1);

        assertThat(commentRepository.findAll()).isNotNull();
        assertThat(commentRepository.findAll().size()).isEqualTo(1);
    }

    @Test
    public void findCommentsByReviewId() {
        Product product = new Product();
        product.setName("Samsung A30");
        product.setPrice(2000.0);
        product.setDescription("Phone");
        product.setId(20);

        Product p = productRepository.save(product);

        Review review = new Review();
        review.setProductId(20);
        review.setReview("Not bad");
        review.setCreatedAt(new Date());
        review.setProductId(p.getId());

        Review r = reviewRepository.save(review);

        Comment comment = new Comment();
        comment.setComment("I like it");
        comment.setCreatedAt(new Date());
        comment.setReviewId(r.getId());

        commentRepository.save(comment);

        assertThat(reviewRepository.findAll()).isNotNull();
        assertThat(reviewRepository.findAll().size()).isEqualTo(1);

        assertThat(commentRepository.findAll()).isNotNull();
        assertThat(commentRepository.findByReviewId(r.getId()).size()).isEqualTo(1);
    }
}
