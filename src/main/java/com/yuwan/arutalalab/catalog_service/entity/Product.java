package com.yuwan.arutalalab.catalog_service.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "products")
@Entity

public class Product {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(unique = true)
    private String sku;

    private String name;

    private BigDecimal price;

    private int stock;

    @Enumerated(EnumType.STRING)
    private ProductStatus status = ProductStatus.ACTIVE;

    public enum ProductStatus {
        ACTIVE,
        INACTIVE
    }

}
