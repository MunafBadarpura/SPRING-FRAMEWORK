package com.munaf.inventory_service.dtos;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class ProductDTO {

    private Long id;

    private String title;

    private Double price;

    private Integer stock;

}
