package com.javadeveloper.order_service.KafkaService.model;


import com.javadeveloper.order_service.Enum.PaymentStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentResultEvent {
    private Long orderId;
    private Long paymentId;
    private PaymentStatus paymentStatus;
    private String reason;
}
