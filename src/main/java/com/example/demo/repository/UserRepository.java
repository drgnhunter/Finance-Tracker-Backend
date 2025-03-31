package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

// Add custom method to find user by username
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);  // Custom method for fetching user by username
}
