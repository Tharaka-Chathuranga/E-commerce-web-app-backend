package com.example.eCommercewebapp.service;

import com.example.eCommercewebapp.model.Order;
import com.example.eCommercewebapp.model.User;
import com.example.eCommercewebapp.model.dao.OrderDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private OrderDAO orderDAO;
    @Autowired
    public OrderService(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    public List<Order> getUserOrder(User user){
        return orderDAO.findByUser(user);
    }

}
