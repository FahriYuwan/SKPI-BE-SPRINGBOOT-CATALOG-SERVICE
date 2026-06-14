package com.yuwan.arutalalab.catalog_service.dto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {

    @NotNull(message = "SKU tidak boleh kosong")
    private String sku;

    @NotNull(message = "Nama produk tidak boleh kosong")
    @Size(min = 3, max = 20)
    private String name;

    @Positive(message = "Harga harus lebih besar daripada 0")
    @NotNull(message = "Harga tidak boleh kosong")
    private BigDecimal price;

    @PositiveOrZero(message = "Stok harus >= 0")
    @NotNull(message = "Stok tidak boleh kosong")
    private int stock;

}
