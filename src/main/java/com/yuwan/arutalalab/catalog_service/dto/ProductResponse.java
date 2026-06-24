package com.yuwan.arutalalab.catalog_service.dto;


import com.yuwan.arutalalab.catalog_service.entity.Product;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class ProductResponse {

    private UUID id;
    private String name;
    private String sku;
    private BigDecimal price;
    private int stock;
    private Product.ProductStatus status;

}
