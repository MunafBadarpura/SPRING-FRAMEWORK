package com.munaf.order_service.dtos;

import lombok.Data;

@Data
public class OrderItemDto {

    private Long id;

    private Long productId;

    private Integer quantity;

}
