package com.javadeveloper.order_service.service;

import org.springframework.stereotype.Service;

import com.javadeveloper.order_service.KafkaService.model.OrderCreatedEvent;
import com.javadeveloper.order_service.model.Orders;

@Service
public class OrderCreatedEventModel {

    public OrderCreatedEvent generateOrderCreatedEvent(Orders orders) {
        OrderCreatedEvent event = new OrderCreatedEvent(orders.getOrderId(), orders.getItemName(), 
        orders.getItemQuantity(), orders.getOrderDate());
        return event;
    }
}
