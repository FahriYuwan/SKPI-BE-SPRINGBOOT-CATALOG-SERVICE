package com.yuwan.arutalalab.catalog_service.dto;

import com.yuwan.arutalalab.catalog_service.entity.Product;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateStatusRequest {

    @NotNull(message = "SKU tidak boleh kosong")
    private Product.ProductStatus status;

}
