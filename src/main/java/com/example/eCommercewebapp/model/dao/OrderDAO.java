package com.example.eCommercewebapp.model.dao;

import com.example.eCommercewebapp.model.Order;
import com.example.eCommercewebapp.model.User;
import org.springframework.data.repository.ListCrudRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface OrderDAO extends ListCrudRepository<Order, Long> {
    List<Order> findByUser(User user);
    Optional<List<Order>> findByDate(LocalDate date);

    Optional<List<Order>> findByStatusOrStatus(String status1, String status2);
}
