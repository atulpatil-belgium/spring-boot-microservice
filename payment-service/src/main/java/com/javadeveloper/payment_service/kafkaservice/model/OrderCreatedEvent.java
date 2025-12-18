package com.javadeveloper.payment_service.kafkaservice.model;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderCreatedEvent {
private Long orderId;
    private String itemName;
    private Long itemQuantity;
    private Date orderDate;
}
