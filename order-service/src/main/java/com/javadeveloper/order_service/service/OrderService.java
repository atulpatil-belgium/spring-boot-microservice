package com.javadeveloper.order_service.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javadeveloper.order_service.Enum.OrderStatus;
import com.javadeveloper.order_service.Enum.PaymentStatus;
import com.javadeveloper.order_service.KafkaService.OrderEventProducer;
import com.javadeveloper.order_service.model.Orders;
import com.javadeveloper.order_service.repository.OrderRepository;

@Service
public class OrderService {

    private OrderRepository orderRepository;
    private OrderEventProducer orderEventProducer;
    private OrderCreatedEventModel eventModel;

    public OrderService(OrderRepository orderRepository,
                        OrderEventProducer orderEventProducer, OrderCreatedEventModel eventModel) {
        this.orderRepository = orderRepository;
        this.orderEventProducer = orderEventProducer;
        this.eventModel = eventModel;
    }

    public List<Orders> getAllorders() {
        return orderRepository.findAll();
    }

    public Orders getOrderDetailsById(Long id) {
        return orderRepository.findById(id).get();
    }

    @Transactional
    public Orders createNewOrder(Orders orders) {
        orders.setOrderStatus(OrderStatus.INPROGRESS);
        orders.setPaymentStatus(PaymentStatus.INPROGRESS);
        Orders savedOrders = orderRepository.save(orders);
        
        //kafka Event...
        orderEventProducer.publishPaymentRequest(eventModel.generateOrderCreatedEvent(savedOrders));

        return savedOrders;
    }

    public List<Orders> findOrdersByUserId(Long userId) {
        return orderRepository.findOrdersByUserId(userId);
    }

    
}
