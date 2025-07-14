package com.munaf.inventory_service.dtos;

import lombok.Data;

@Data
public class OrderItemDto {

    private Long productId;
    private Integer quantity;

}
