package com.example.eCommercewebapp.service;

import com.example.eCommercewebapp.api.model.OrderBody;
import com.example.eCommercewebapp.model.Order;
import com.example.eCommercewebapp.model.User;
import com.example.eCommercewebapp.model.dao.OrderDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
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

    public Order createOrder(User user, OrderBody orderBody){
        LocalDate localDate = LocalDate.now();
        Order order = new Order();
        order.setUser(user);
        order.setStatus(orderBody.getStatus());
        order.setItem(orderBody.getItem());
        order.setQuantity(orderBody.getQuantity());
        order.setDate(localDate);
        orderDAO.save(order);
        return order;
    }

}
