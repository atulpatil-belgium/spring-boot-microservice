package com.javadeveloper.notification_service.kafkaservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentStatusEvent {
    private Long orderId;
    private Long paymentId;
    private PaymentStatus paymentStatus;
    private String reason;
}
