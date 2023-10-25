package com.example.eCommercewebapp.api.controller.order;

import com.example.eCommercewebapp.api.model.OrderBody;
import com.example.eCommercewebapp.model.Order;
import com.example.eCommercewebapp.model.User;
import com.example.eCommercewebapp.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @DeleteMapping("/deleteUserOrder")
    public Order deleteUserOrder(@AuthenticationPrincipal User user, @RequestBody @Valid OrderBody orderBody){
        return orderService.deleteUserOrder(user,orderBody);
    }


    @GetMapping("/userOrders")
    public List<Order> getUserOrder(@AuthenticationPrincipal User user){
        return orderService.getUserOrder(user);
    }

    @PutMapping("/changeOrder")
    public ResponseEntity<Order> changeOrderStatus(@AuthenticationPrincipal User user, @RequestBody OrderBody orderBody){
       Order updatedorder = orderService.changeOrderStatus(user, orderBody);

        if (updatedorder==null){
           return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } else{
            return ResponseEntity.ok(updatedorder);

        }
    }
}
