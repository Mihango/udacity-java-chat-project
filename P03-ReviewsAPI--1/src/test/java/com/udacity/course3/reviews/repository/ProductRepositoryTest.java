package com.udacity.course3.reviews.repository;

import com.udacity.course3.reviews.models.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProductRepositoryTest {

    @Autowired private EntityManager entityManager;
    @Autowired private ProductRepository productRepository;

    @Test
    public void injectedComponentsNotNull() {
        assertThat(entityManager).isNotNull();
        assertThat(productRepository).isNotNull();
    }

    @Test
    public void testFindProductByName() {
        Product product = new Product();
        product.setName("Samsung A30");
        product.setPrice(2000.0);
        product.setDescription("Phone");

        entityManager.persist(product);

        Optional<Product> optional = productRepository.findByName("Samsung A30");
        assertThat(optional.get()).isNotNull();
    }

    @Test
    public void testFindAllProducts_returns_List() {
        List<Product> products = productRepository.findAll();
        assertThat(products).isNotNull();
        assertThat(products.size()).isEqualTo(0);
    }
}
