package com.yuwan.arutalalab.catalog_service.service;

import com.yuwan.arutalalab.catalog_service.dto.ProductRequest;
import com.yuwan.arutalalab.catalog_service.dto.ProductResponse;
import com.yuwan.arutalalab.catalog_service.dto.UpdateStatusRequest;
import com.yuwan.arutalalab.catalog_service.dto.UpdateStockRequest;
import com.yuwan.arutalalab.catalog_service.entity.Product;
import com.yuwan.arutalalab.catalog_service.exception.DuplicateSkuException;
import com.yuwan.arutalalab.catalog_service.exception.ProductNotFoundException;
import com.yuwan.arutalalab.catalog_service.repository.ProductRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public ProductResponse addProduct(@Valid ProductRequest product) {
        if (productRepository.findBySku(product.getSku()).isPresent()) {
            throw new DuplicateSkuException(product.getSku());
        }

        Product newProduct = new Product();
        newProduct.setSku(product.getSku());
        newProduct.setName(product.getName());
        newProduct.setPrice(product.getPrice());
        newProduct.setStock(product.getStock());

        Product savedProduct = productRepository.save(newProduct);
        return toProductResponse(savedProduct);
    }

    @Transactional
    public ProductResponse updateStock(UUID id, @Valid UpdateStockRequest request){
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isEmpty()) {
            throw new ProductNotFoundException(id);
        }
        Product product = productOptional.get();
        product.setStock(request.getStock());
        Product savedProduct = productRepository.save(product);
        return toProductResponse(savedProduct);
    }

    @Transactional
    public ProductResponse updateStatus(UUID id, @Valid UpdateStatusRequest request){
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isEmpty()) {
            throw new ProductNotFoundException(id);
        }
        Product product = productOptional.get();
        product.setStatus(request.getStatus());
        Product savedProduct = productRepository.save(product);
        return toProductResponse(savedProduct);
    }

    @NonNull
    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(this::toProductResponse)
                .toList();
    }

    @NonNull
    private ProductResponse toProductResponse(Product product) {
        ProductResponse productResponse = new ProductResponse();
        productResponse.setId(product.getId());
        productResponse.setSku(product.getSku());
        productResponse.setName(product.getName());
        productResponse.setPrice(product.getPrice());
        productResponse.setStock(product.getStock());
        productResponse.setStatus(product.getStatus());
        return productResponse;
    }


}
