package com.yuwan.arutalalab.catalog_service.service;

import com.yuwan.arutalalab.catalog_service.dto.ProductRequest;
import com.yuwan.arutalalab.catalog_service.dto.ProductResponse;
import com.yuwan.arutalalab.catalog_service.entity.Product;
import com.yuwan.arutalalab.catalog_service.repository.ProductRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public ProductResponse addProduct(@Valid ProductRequest product) {
        if (productRepository.findBySku(product.getSku()).isEmpty()) {
            productRepository.save(product);
        }else{
            throw new RuntimeException("SKU sudah terdaftar: " + request.getSku());
        }

        ProductResponse productResponse = new ProductResponse();
        productResponse.setId(product.getId());
        productResponse.setName(product.getName());
        productResponse.setPrice(product.getPrice());
        return productResponse;
    }

    @Transactional
    public ProductResponse updateProduct(Product product) {
        if(productRepository.existsById(product.getId())){
            if(productRepository.findBySku(product.getSku()).isEmpty()) {
                productRepository.save(product);
            }else{
                throw new RuntimeException("SKU sudah terdaftar: " + request.getSku());
            }
        }else{
            throw new RuntimeException("Produk yang Anda cari tidak ditemukan");
        }
        ProductResponse productResponse = new ProductResponse();
        productResponse.setId(product.getId());
        productResponse.setName(product.getName());
        productResponse.setPrice(product.getPrice());
        return productResponse;
    }

    @Transactional
    public ProductResponse deleteProduct(Product product) {
        if(productRepository.existsById(product.getId())) {
            productRepository.deleteById(product.getId());
        }else{
            System.out.println("Produk yang Anda cari tidak ditemukan");
        }
        ProductResponse productResponse = new ProductResponse();
        productResponse.setId(product.getId());
        productResponse.setName(product.getName());
        productResponse.setPrice(product.getPrice());
        return productResponse;
    }

    public ProductResponse getProduct(Product product) {
        ProductResponse productResponse = new ProductResponse();
        productResponse.setId(product.getId());
        productResponse.setName(product.getName());
        productResponse.setPrice(product.getPrice());
        return productResponse;
    }

}
