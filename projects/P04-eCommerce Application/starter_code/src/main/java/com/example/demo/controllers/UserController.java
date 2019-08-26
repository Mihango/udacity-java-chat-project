package com.example.demo.controllers;

import com.example.demo.model.persistence.EcommerceUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.persistence.Cart;
import com.example.demo.model.persistence.repositories.CartRepository;
import com.example.demo.model.persistence.repositories.UserRepository;
import com.example.demo.model.requests.CreateUserRequest;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/id/{id}")
    public ResponseEntity<EcommerceUser> findById(@PathVariable Long id) {
        return ResponseEntity.of(userRepository.findById(id));
    }

    @GetMapping("/{username}")
    public ResponseEntity<EcommerceUser> findByUserName(@PathVariable String username) {
        EcommerceUser ecommerceUser = userRepository.findByUsername(username);
        return ecommerceUser == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(ecommerceUser);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@Valid @RequestBody CreateUserRequest createUserRequest) {
        log.info("Creating user {}", createUserRequest.getUsername());

        if (!createUserRequest.getPassword().equals(createUserRequest.getConfirmPassword()) || createUserRequest.getPassword().length() < 7) {
            log.error("Error with user password. Cannot create user {}", createUserRequest.getUsername());
        	return ResponseEntity.badRequest().body("Password is not correct");
        }

        EcommerceUser ecommerceUser = new EcommerceUser();
        ecommerceUser.setUsername(createUserRequest.getUsername());
        ecommerceUser.setPassword(passwordEncoder.encode(createUserRequest.getPassword()));

        // create random salt

        Cart cart = new Cart();
        cartRepository.save(cart);
        ecommerceUser.setCart(cart);
        userRepository.save(ecommerceUser);
        return ResponseEntity.ok(ecommerceUser);
    }

}
