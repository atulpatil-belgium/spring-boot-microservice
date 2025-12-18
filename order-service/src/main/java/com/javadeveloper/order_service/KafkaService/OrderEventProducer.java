package com.javadeveloper.order_service.KafkaService;


import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.javadeveloper.order_service.KafkaService.model.OrderCreatedEvent;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

@Service
@EnableKafka
public class OrderEventProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

 private int counter = 0;

    public OrderEventProducer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Retry(name = "payemtRetry")
     @CircuitBreaker(name = "orderBreakerService", fallbackMethod = "fallback")
    public void publishPaymentRequest(OrderCreatedEvent orders) {
        System.out.println("Publishing order payment event: " + orders.getOrderId());
       try {
        counter++;
        System.out.println("Attempt #" + counter);

        // Fail first 3 attempts
        if (counter <= 3) {
            throw new RuntimeException("Simulated failure");
        }
 
        kafkaTemplate.send("payment-request", orders.getOrderId().toString(), orders).get();
       } catch (Exception e) {
         throw new RuntimeException("Kafka broker down", e);
       }
    }

    public void fallback(OrderCreatedEvent event, RuntimeException t) {
        System.out.println("Circuit breaker triggered: " + t.getMessage());
    }

}
