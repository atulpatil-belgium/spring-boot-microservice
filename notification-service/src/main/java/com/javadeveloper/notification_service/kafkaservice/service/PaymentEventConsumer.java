package com.javadeveloper.notification_service.kafkaservice.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javadeveloper.notification_service.kafkaservice.model.PaymentStatusEvent;
import com.javadeveloper.notification_service.service.NotificationService;


@Service
public class PaymentEventConsumer {


    private NotificationService notificationService;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public PaymentEventConsumer(NotificationService notificationService, KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
        this.notificationService = notificationService;
    }


    @KafkaListener(topics = "payment-status-result", groupId = "notification-service", containerFactory = "kafkaListenerContainerFactory")
    @Transactional
    public void consume(PaymentStatusEvent event) {
        System.out.println("Event Receive from Payment service ..... " + event.getOrderId());
        boolean notificationFlag = notificationService.sendOrderCreatedEmail(event);
        if(notificationFlag) {
            System.out.println("Notification sent ..... Event send to Order service to update the status.....");
            kafkaTemplate.send("nofication-sent", event.getOrderId().toString(), event );
        }
    }
}
