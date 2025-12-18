package com.javadeveloper.payment_service.kafkaservice.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javadeveloper.payment_service.kafkaservice.model.OrderCreatedEvent;
import com.javadeveloper.payment_service.service.PaymentService;

@Service
@Component
public class OrderEventConsumer {

    private PaymentService paymentService;
    private PaymentEventProducer paymentEventProducer;

    public OrderEventConsumer(PaymentService paymentService, PaymentEventProducer paymentEventProducer) {
        this.paymentEventProducer = paymentEventProducer;
        this.paymentService = paymentService;
    }

    @KafkaListener(topics = "payment-request", groupId = "payment-service")
    public void consume(String message) throws Exception { //consume(OrderCreatedEvent event) {
        ObjectMapper objectMapper = new ObjectMapper();
        OrderCreatedEvent event =
        objectMapper.readValue(message, OrderCreatedEvent.class);
        System.out.println("calling payment event... " + event.getOrderId());
        paymentEventProducer.publishPaymentEvent(paymentService.processOrderPayment(event));
    }

}
