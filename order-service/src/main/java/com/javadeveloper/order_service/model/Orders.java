package com.javadeveloper.order_service.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.javadeveloper.order_service.Enum.OrderStatus;
import com.javadeveloper.order_service.Enum.PaymentStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderId;

    private String itemName;
    private Long itemQuantity;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-mm-dd")
    private Date orderDate;

    private Long userId;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;
    
}
