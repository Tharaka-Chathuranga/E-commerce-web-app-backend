package com.example.eCommercewebapp.model.dao;

import com.example.eCommercewebapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserDAO extends JpaRepository<User, Long> {
    Optional<User> findByEmailIgnoreCase (String email);
    Optional<List<User>> findByCreatedDateAndRole(LocalDate date,String role);

    Optional<List<User>>  findByRole(String role);
}
