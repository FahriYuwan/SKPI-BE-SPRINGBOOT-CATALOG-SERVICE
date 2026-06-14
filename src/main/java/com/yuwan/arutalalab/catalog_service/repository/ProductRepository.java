package com.yuwan.arutalalab.catalog_service.repository;

import com.yuwan.arutalalab.catalog_service.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {

    //Mencari product berdasarkan sku
    Optional<Product> findBySku(String sku);

    //Mencari product berdasarkan nama
    Optional<Product> findByName(String name);

}
