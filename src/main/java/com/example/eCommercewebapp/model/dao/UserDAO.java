package com.example.eCommercewebapp.model.dao;

import com.example.eCommercewebapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDAO extends JpaRepository<User, Long> {
    Optional<User> findByEmailIgnoreCase (String email);
}
