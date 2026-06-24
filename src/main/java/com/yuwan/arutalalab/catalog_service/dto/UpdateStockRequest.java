package com.yuwan.arutalalab.catalog_service.dto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateStockRequest {

    @PositiveOrZero(message = "Stok harus >= 0")
    @NotNull(message = "Stok tidak boleh kosong")
    private int stock;

}
