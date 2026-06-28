package com.yuwan.arutalalab.catalog_service.exception;

public class DuplicateSkuException extends RuntimeException {

    public DuplicateSkuException(String sku) {
        super("SKU sudah terdaftar: " + sku);
    }
}
