package com.example.eCommercewebapp.model.dao;

import com.example.eCommercewebapp.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserDAO extends CrudRepository<User, Long> {
    Optional<User> findByEmailIgnoreCase (String email);
}
