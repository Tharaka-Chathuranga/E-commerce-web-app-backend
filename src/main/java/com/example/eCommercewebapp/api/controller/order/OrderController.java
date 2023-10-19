package com.example.eCommercewebapp.api.controller.order;

import com.example.eCommercewebapp.model.Order;
import com.example.eCommercewebapp.model.User;
import com.example.eCommercewebapp.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private  OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

//    public ResponseEntity createOrder(@AuthenticationPrincipal User user @RequestBody )

    @GetMapping("/userOrders")
    public List<Order> getUserOrder(@AuthenticationPrincipal User user){
        return orderService.getUserOrder(user);
    }
}
