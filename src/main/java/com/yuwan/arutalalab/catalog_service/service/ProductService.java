package com.yuwan.arutalalab.catalog_service.service;

import com.yuwan.arutalalab.catalog_service.dto.ProductRequest;
import com.yuwan.arutalalab.catalog_service.dto.ProductResponse;
import com.yuwan.arutalalab.catalog_service.dto.UpdateStatusRequest;
import com.yuwan.arutalalab.catalog_service.dto.UpdateStockRequest;
import com.yuwan.arutalalab.catalog_service.entity.Product;
import com.yuwan.arutalalab.catalog_service.repository.ProductRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.hibernate.sql.Update;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public ProductResponse addProduct(@Valid ProductRequest product) {
        if (productRepository.findBySku(product.getSku()).isEmpty()) {
            Product newProduct = new Product();
            newProduct.setSku(product.getSku());
            productRepository.save(newProduct);
        }else{
            throw new RuntimeException("SKU sudah terdaftar: " + product.getSku());
        }

        ProductResponse productResponse = new ProductResponse();
        productResponse.setSku(product.getSku());
        productResponse.setName(product.getName());
        productResponse.setPrice(product.getPrice());
        productResponse.setStock(product.getStock());
        return productResponse;
    }

//    @Transactional
//    public ProductResponse updateProduct(Product product) {
//        if(productRepository.existsById(product.getId())){
//            if(productRepository.findBySku(product.getSku()).isEmpty()) {
//                productRepository.save(product);
//            }else{
//                throw new RuntimeException("SKU sudah terdaftar: " + request.getSku());
//            }
//        }else{
//            throw new RuntimeException("Produk yang Anda cari tidak ditemukan");
//        }
//        ProductResponse productResponse = new ProductResponse();
//        productResponse.setId(product.getId());
//        productResponse.setName(product.getName());
//        productResponse.setPrice(product.getPrice());
//        return productResponse;
//    }

    @Transactional
    public ProductResponse updateStock(UUID id, @Valid UpdateStockRequest request){
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isEmpty()) {
            throw new RuntimeException("Product not found: " + id);
        }
        Product product = productOptional.get();
        product.setStock(request.getStock());
        return getProductResponse(product);
    }

    @Transactional
    public ProductResponse updateStatus(UUID id, @Valid UpdateStatusRequest request){
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isEmpty()) {
            throw new RuntimeException("Product not found: " + id);
        }
        Product product = productOptional.get();
        product.setStatus(request.getStatus());
        return getProductResponse(product);
    }

    @NonNull
    private ProductResponse getProductResponse(Product product) {
        productRepository.save(product);
        ProductResponse productResponse = new ProductResponse();
        productResponse.setSku(product.getSku());
        productResponse.setName(product.getName());
        productResponse.setPrice(product.getPrice());
        productResponse.setStock(product.getStock());
        return productResponse;
    }


}
