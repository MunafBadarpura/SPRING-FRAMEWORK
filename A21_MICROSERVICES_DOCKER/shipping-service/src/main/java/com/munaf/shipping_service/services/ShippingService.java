package com.munaf.shipping_service.services;

import com.munaf.shipping_service.dtos.OrderDto;
import com.munaf.shipping_service.entities.Order;
import com.munaf.shipping_service.entities.enums.OrderStatus;
import com.munaf.shipping_service.repositories.OrderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ShippingService {

    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;

    public ShippingService(OrderRepository orderRepository, ModelMapper modelMapper) {
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
    }


    public OrderDto shippingOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with id : " + orderId));

        order.setOrderStatus(OrderStatus.SHIPPED);
        order = orderRepository.save(order);

        return modelMapper.map(order, OrderDto.class);
    }


}
