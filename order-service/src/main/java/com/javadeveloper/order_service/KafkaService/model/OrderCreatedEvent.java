package com.javadeveloper.order_service.KafkaService.model;

import java.util.Date;

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
