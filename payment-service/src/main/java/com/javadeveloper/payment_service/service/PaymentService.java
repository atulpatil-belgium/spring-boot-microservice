package com.javadeveloper.payment_service.service;

import org.springframework.stereotype.Service;

import com.javadeveloper.payment_service.kafkaservice.Enum.PaymentStatus;
import com.javadeveloper.payment_service.kafkaservice.model.OrderCreatedEvent;
import com.javadeveloper.payment_service.kafkaservice.model.PaymentStatusEvent;

@Service
public class PaymentService {

    public PaymentStatusEvent processOrderPayment(OrderCreatedEvent createdEvent) {
        PaymentStatusEvent statusEvent = new PaymentStatusEvent();
        statusEvent.setOrderId(createdEvent.getOrderId());
        statusEvent.setPaymentStatus(PaymentStatus.INPROGRESS);
        if(createdEvent.getItemName() != null && createdEvent.getItemQuantity() > 0) {
            
            statusEvent.setPaymentId(1L);
            statusEvent.setPaymentStatus(PaymentStatus.DONE);
        }else {

           statusEvent.setPaymentStatus(PaymentStatus.FAILED);
           statusEvent.setReason("Oder quantity is less than zero!");
        }
        System.out.println("Payment status == " + statusEvent.getPaymentStatus());
        return statusEvent;
    }
    
}
