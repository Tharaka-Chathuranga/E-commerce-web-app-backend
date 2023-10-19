package com.example.eCommercewebapp.api.controller.order;

import com.example.eCommercewebapp.api.model.OrderBody;
import com.example.eCommercewebapp.model.Order;
import com.example.eCommercewebapp.model.User;
import com.example.eCommercewebapp.service.OrderService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/createOrder")
    public Order createOrder(@AuthenticationPrincipal User user ,@RequestBody OrderBody orderBody){

        return orderService.createOrder(user, orderBody);
    }


    @GetMapping("/userOrders")
    public List<Order> getUserOrder(@AuthenticationPrincipal User user){
        return orderService.getUserOrder(user);
    }
}
