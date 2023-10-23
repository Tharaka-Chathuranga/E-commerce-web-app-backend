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

//        Optional<User> loadUser = userDAO.findByEmailIgnoreCase(user.getEmail());
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

//        if (loadUser.isPresent()){
//            User exsistingUser = loadUser.get();
//            LocalDate localDate = LocalDate.now();
//            Order order = new Order();
//            order.setStatus(orderBody.getStatus());
//            order.setItem(orderBody.getItem());
//            order.setQuantity(orderBody.getQuantity());
//            order.setDate(localDate);
//            exsistingUser.getOrders().add(order);
//            orderDAO.save(order);
//
//            return order;
//        }
//        return null;






}
