package com.yuwan.arutalalab.catalog_service.service;

import com.yuwan.arutalalab.catalog_service.dto.ProductResponse;
import com.yuwan.arutalalab.catalog_service.entity.Product;
import com.yuwan.arutalalab.catalog_service.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public ProductResponse addProduct(Product product) {
        if (productRepository.findBySku(product.getSku()).isEmpty()) {
            productRepository.save(product);
        }else{
            System.out.println("SKU sudah digunakan, gunakan SKU lain");

        }

    }

}
