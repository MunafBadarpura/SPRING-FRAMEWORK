package com.munaf.order_service.dtos;

import com.munaf.order_service.entities.enums.OrderStatus;
import lombok.Data;

import java.util.List;

@Data
public class OrderDto {

    private Long id;

    private List<OrderItemDto> orderItems;

    private Double totalPrice;

    private OrderStatus orderStatus;

}
