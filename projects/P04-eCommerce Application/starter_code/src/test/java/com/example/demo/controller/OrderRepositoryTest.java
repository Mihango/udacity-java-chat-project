package com.example.demo.controller;


import com.example.demo.model.persistence.Cart;
import com.example.demo.model.persistence.EcommerceUser;
import com.example.demo.model.persistence.Item;
import com.example.demo.model.persistence.UserOrder;
import com.example.demo.model.persistence.repositories.OrderRepository;
import com.example.demo.model.persistence.repositories.UserRepository;
import com.example.demo.security.WebSecurity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.net.URI;
import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = {OrderRepository.class})
@Import(WebSecurity.class)
public class OrderRepositoryTest {

    // todo add security

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderRepository orderRepository;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private UserDetailsService userDetailsService;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @Before
    public void setup() {
        Mockito.when(orderRepository.findByEcommerceUser(getTestUser())).thenReturn(Collections.singletonList(getTestOrder()));
        Mockito.when(userRepository.findByUsername("Sareeta")).thenReturn(getTestUser());
    }

    @Test
    public void submit_new_order_expect_success() throws Exception {
        mockMvc.perform(
                post(new URI("/api/order/submit/Sareeta"))
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
        )
        .andExpect(status().isOk());
    }

    @Test
    public void submit_new_empty_order() throws Exception {
        mockMvc.perform(
                post(new URI("/api/order/submit/Sareeta"))
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(status().isBadRequest());
    }

    private UserOrder getTestOrder() {
        UserOrder userOrder = new UserOrder();

        EcommerceUser user = new EcommerceUser();
        user.setId(1);
        user.setUsername("Sareeta");

        Item item = new Item();
        item.setId(1L);

        userOrder.setId(1L);
        userOrder.setTotal(BigDecimal.valueOf(2.99));
        userOrder.setItems(Collections.singletonList(item));
        userOrder.setEcommerceUser(user);

        return userOrder;
    }

    private EcommerceUser getTestUser() {
        EcommerceUser user = new EcommerceUser();
        user.setId(1);
        user.setUsername("Sareeta");

        Cart cart = new Cart();
        cart.addItem(new Item());

        user.setCart(cart);
        return user;
    }
}
