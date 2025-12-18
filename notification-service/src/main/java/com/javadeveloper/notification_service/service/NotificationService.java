package com.javadeveloper.notification_service.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javadeveloper.notification_service.kafkaservice.model.PaymentStatus;
import com.javadeveloper.notification_service.kafkaservice.model.PaymentStatusEvent;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class NotificationService {

    private static int counter = 0;

    @Transactional
    @CircuitBreaker(name = "notifyService", fallbackMethod = "sendNotificationFallback")
    public boolean sendOrderCreatedEmail(PaymentStatusEvent event) {

        counter++;
        System.out.println("Sending email for order " + event.getOrderId());

        if (counter <= 3) {
            System.out.println("Simulated email failure");
            throw new RuntimeException("SMTP server down");
        }

        String subject = "Order Created Successfully";
        String body = "";
        if(event.getPaymentStatus().equals(PaymentStatus.DONE)) {
            body = """
                Hi,
                Your order %s has been created successfully.
                Thank you for shopping with us!
                """.formatted(
                        event.getOrderId()
                );
        } else {
            body = """
                Hi,
                Your order %s is creted due to reason %s.                
                Thank you for shopping with us!
                """.formatted(
                        event.getOrderId(), event.getReason()
                );
        }

        System.out.println(subject);
        System.out.println("/----------------------------------------------------/");
        System.out.println(body);
        System.out.println("Email sent successfully for order " + event.getOrderId());
        return true;
    }

    public void sendNotificationFallback(Long orderId, Throwable ex) {
        System.out.println("⚠️ Email fallback triggered for order " + orderId);
    }
}
