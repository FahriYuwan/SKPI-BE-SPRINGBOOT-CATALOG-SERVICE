package com.yuwan.arutalalab.catalog_service.controller;


import com.yuwan.arutalalab.catalog_service.dto.ProductRequest;
import com.yuwan.arutalalab.catalog_service.dto.ProductResponse;
import com.yuwan.arutalalab.catalog_service.dto.UpdateStatusRequest;
import com.yuwan.arutalalab.catalog_service.dto.UpdateStockRequest;
import com.yuwan.arutalalab.catalog_service.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    //Constructor Injection
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    //Membuat produk baru
    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@Valid @RequestBody ProductRequest request){
        ProductResponse productResponse = productService.addProduct(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(productResponse);
    }

    //Menampilkan semua produk
    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts(){
        return ResponseEntity.ok(productService.getAllProducts());
    }

    //Memperbarui stok produk
    @PutMapping("/{id}/stock")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable UUID id, @Valid @RequestBody UpdateStockRequest request){
        return ResponseEntity.ok(productService.updateStock(id, request));
    }

    //Memperbarui status produk
    @PutMapping("/{id}/status")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable UUID id, @Valid @RequestBody UpdateStatusRequest request){
        return ResponseEntity.ok(productService.updateStatus(id, request));
    }




}
