package com.munaf.order_service.dtos;

import com.munaf.order_service.entities.OrderItem;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class OrderRequestDto {

    private Long id;

    private List<OrderItemRequestDto> orderItemRequestDtos;

    private BigDecimal totalPrice;
}
