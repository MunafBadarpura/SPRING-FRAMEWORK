package com.munaf.A10_SPRING_DATA_JPA.A10_SPRING_DATA_JPA.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(
        name = "product_table",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"sku"}),
                @UniqueConstraint(columnNames = {"name", "price"})
                // name with price should be unique like if name parle with price 20 exist so new parle with price 50 is valid
        },
        indexes = {
                @Index(name = "sku_index", columnList = "sku"),
        }
)
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sku;

    @Column(name = "product_name", nullable = false, length = 25)
    private String name;

    private BigDecimal price;

    private Integer quantity;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

}
