package com.yuwan.arutalalab.catalog_service.controller;


import com.yuwan.arutalalab.catalog_service.dto.ProductRequest;
import com.yuwan.arutalalab.catalog_service.dto.ProductResponse;
import com.yuwan.arutalalab.catalog_service.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    //Menampilkan produk

}
