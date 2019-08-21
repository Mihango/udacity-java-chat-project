package com.udacity.course3.reviews.repository;

import com.udacity.course3.reviews.models.relational.Product;
import com.udacity.course3.reviews.models.relational.Review;
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
public class ReviewRepositoryTest {

    @Autowired ReviewRepository reviewRepository;
    @Autowired ProductRepository productRepository;

    @Test
    public void injectedNotNull() {
        assertThat(reviewRepository).isNotNull();
        assertThat(productRepository).isNotNull();
    }

    @Test
    public void findReviews() {
        Product product = testData();

        Product p = productRepository.save(product);
        product.getReviews().get(0).setProductId(p.getId());
        reviewRepository.saveAll(product.getReviews());

        assertThat(reviewRepository.findAll()).isNotNull();
        assertThat(reviewRepository.findAll().size()).isEqualTo(1);
    }

    @Test
    public void findReviewsByProduct() {
        Product product = testData();

        Product p = productRepository.save(product);
        product.getReviews().get(0).setProductId(p.getId());
        reviewRepository.saveAll(product.getReviews());

        assertThat(reviewRepository.findAll()).isNotNull();
        assertThat(reviewRepository.findAllByProductId(p.getId()).size()).isEqualTo(1);
    }

    private Product testData() {
        Product product = new Product();
        product.setName("Samsung A30");
        product.setPrice(2000.0);
        product.setDescription("Phone");
        product.setId(20);

        Review review = new Review();
        review.setProductId(20);
        review.setReview("Not bad");
        review.setCreatedAt(new Date());

        List<Review> reviews = new ArrayList<>();

        reviews.add(review);
        product.setReviews(reviews);

        return product;
    }
}
