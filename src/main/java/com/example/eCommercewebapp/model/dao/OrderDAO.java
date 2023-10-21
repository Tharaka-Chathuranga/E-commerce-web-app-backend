package com.example.eCommercewebapp.model.dao;

import com.example.eCommercewebapp.model.Order;
import com.example.eCommercewebapp.model.User;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface OrderDAO extends ListCrudRepository<Order, Long> {
    List<Order> findByUser(User user);
}
