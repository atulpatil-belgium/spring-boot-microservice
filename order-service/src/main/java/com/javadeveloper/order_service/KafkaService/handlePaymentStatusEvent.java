package com.javadeveloper.order_service.KafkaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javadeveloper.order_service.Enum.OrderStatus;
import com.javadeveloper.order_service.Enum.PaymentStatus;
import com.javadeveloper.order_service.KafkaService.model.PaymentResultEvent;
import com.javadeveloper.order_service.repository.OrderRepository;


@Service
public class handlePaymentStatusEvent {

    @Autowired
    private OrderRepository orderRepository;

    @KafkaListener(topics = "nofication-sent", groupId = "order-service", containerFactory = "kafkaListenerContainerFactory")
    @Transactional
    public void handlePaymentResult( PaymentResultEvent event) {
    System.out.println("Received payment event in order service");

        if (event.getPaymentStatus() == PaymentStatus.DONE) {
            orderRepository.updateStatus(
                event.getOrderId(),
                OrderStatus.ORDERD,
                PaymentStatus.DONE
            );
        } else {
            orderRepository.updateStatus(
                event.getOrderId(),
                OrderStatus.FAILED,
                PaymentStatus.FAILED
            );
        }
        
        System.out.println("calling and sending notification service");
       
    }
}
