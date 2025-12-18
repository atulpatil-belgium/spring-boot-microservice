package com.javadeveloper.payment_service.kafkaservice.model;

import com.javadeveloper.payment_service.kafkaservice.Enum.PaymentStatus;

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
