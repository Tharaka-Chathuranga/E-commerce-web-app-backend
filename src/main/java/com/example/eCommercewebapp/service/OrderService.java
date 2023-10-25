package com.example.eCommercewebapp.service;

import com.example.eCommercewebapp.api.model.OrderBody;
import com.example.eCommercewebapp.model.Order;
import com.example.eCommercewebapp.model.User;
import com.example.eCommercewebapp.model.dao.OrderDAO;
import com.example.eCommercewebapp.model.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderDAO orderDAO;
    private final UserDAO userDAO;
    @Autowired
    public OrderService(OrderDAO orderDAO, UserDAO userDAO) {
        this.orderDAO = orderDAO;
        this.userDAO = userDAO;
    }

    public List<Order> getUserOrder(User user){
        return orderDAO.findByUser(user);
    }

    public Order createOrder(User user, OrderBody orderBody) {

        User exsistingUser = userDAO.findById(user.getId()).get();
        Order newOrder = orderDAO.save(Order.builder()
                .status(orderBody.getStatus())
                .date(LocalDate.now())
                .quantity(orderBody.getQuantity())
                .user(exsistingUser)
                .item(orderBody.getItem())
                .build());
        if (newOrder != null) {

            exsistingUser.getOrders().add(newOrder);
            userDAO.save(exsistingUser);
            return newOrder;
        }
        return null;
    }

   public Order changeOrderStatus(User user, OrderBody orderBody) {
    Order existingOrder = orderDAO.findById(orderBody.getId()).orElse(null);

    if (existingOrder == null) {
        return null;
    }

    if ("admin".equals(user.getRole())) {
        existingOrder.setStatus(orderBody.getStatus());
    } else if ("customer".equals(user.getRole())) {
        existingOrder.setStatus(orderBody.getStatus());
        existingOrder.setQuantity(orderBody.getQuantity());
    } else {
        return null;
    }

    orderDAO.save(existingOrder);
    return existingOrder;

    }

public Order deleteUserOrder(User user, OrderBody orderBody) {
    System.out.println(orderBody.getId());
    // Find the existing order by ID
    Order existingOrder = orderDAO.findById(orderBody.getId()).orElse(null);

    if (existingOrder != null) {
        // Delete the existing order from the database
        orderDAO.delete(existingOrder);
        return existingOrder;
    } else {
        // Order not found, return null
        return null;
    }
}



}
