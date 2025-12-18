package com.javadeveloper.order_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javadeveloper.order_service.model.Orders;
import com.javadeveloper.order_service.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/order-service")
public class OrderController {

    @Autowired
    private OrderService orderService;


    @GetMapping("/get-orders")
    public List<Orders> getAllOrders() {
        return orderService.getAllorders();
    }

    @GetMapping("/get-order/{id}")
    public Orders getOrderById(@PathVariable Long id) {
        return orderService.getOrderDetailsById(id);
    }

    @PostMapping("/create-new-order")
    public Orders createNewOrder(@RequestBody Orders orders) {
        return orderService.createNewOrder(orders);
    }

    @GetMapping("/get-order-by-user-id/{userId}")
    public List<Orders> getOrderDetailsByUserID(@PathVariable Long userId) {
        return orderService.findOrdersByUserId(userId);
    }
    
    
    
    
}
