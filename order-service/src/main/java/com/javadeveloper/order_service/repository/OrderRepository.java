package com.javadeveloper.order_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.javadeveloper.order_service.Enum.OrderStatus;
import com.javadeveloper.order_service.Enum.PaymentStatus;
import com.javadeveloper.order_service.model.Orders;

@Transactional
public interface OrderRepository extends JpaRepository<Orders, Long>{
    List<Orders> findOrdersByUserId(Long userId);

    @Modifying
    @Query("""
        UPDATE Orders o
        SET o.orderStatus = :orderStatus,
            o.paymentStatus = :paymentStatus
        WHERE o.orderId = :orderId
    """)
    int updateStatus(@Param("orderId") Long orderId,
                     @Param("orderStatus") OrderStatus orderStatus,
                     @Param("paymentStatus") PaymentStatus paymentStatus);
}
