package com.javadeveloper.payment_service.kafkaservice.service;


import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.javadeveloper.payment_service.kafkaservice.model.PaymentStatusEvent;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;


@Service
public class PaymentEventProducer {

    private static int counter = 0;
    private final KafkaTemplate<String, PaymentStatusEvent> kafkaTemplate;

    public PaymentEventProducer(KafkaTemplate<String, PaymentStatusEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Retry(name = "notificationRetry")
     @CircuitBreaker(name = "notificationBreakerService", fallbackMethod = "fallback")
    public void  publishPaymentEvent(PaymentStatusEvent event) {
        try {
            counter++;
        System.out.println("Attempt #" + counter);

        // Fail first 3 attempts
        if (counter <= 3) {
            throw new RuntimeException("Simulated failure");
        }
            kafkaTemplate.send("order-notification", event.getOrderId().toString(), event).get();
            System.out.println("Proceed !!!!!!");  
        } catch (Exception e) {
             throw new RuntimeException("Kafka broker down", e);
        }
    }

    public void fallback(PaymentStatusEvent event, RuntimeException t) {
        System.out.println("Circuit breaker triggered: " + t.getMessage());
    }
}
