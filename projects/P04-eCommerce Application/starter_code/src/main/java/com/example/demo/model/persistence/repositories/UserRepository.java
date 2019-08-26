package com.example.demo.model.persistence.repositories;

import com.example.demo.model.persistence.EcommerceUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<EcommerceUser, Long> {
	EcommerceUser findByUsername(String username);
}
