package com.munaf.inventory_service.dtos;

import lombok.Data;

import java.util.List;

@Data
public class OrderDto {

    private List<OrderItemDto> orderItems;

}
