package com.munaf.shipping_service.repositories;

import com.munaf.shipping_service.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
