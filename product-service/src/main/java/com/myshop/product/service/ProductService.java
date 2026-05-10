package com.myshop.product.service;

import com.myshop.product.dto.ProductDto;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    //create
    ProductDto create(ProductDto productDto, UUID categoryId);

    //update
    ProductDto update(UUID uuid, ProductDto productDto);

    //delete
    boolean delete(UUID uuid);

    //get all
    List<ProductDto> getAllProduct();

    //get single by id
    ProductDto getProductById(UUID uuid);

    //get products by category
    List<ProductDto> getProductByCategoryId(UUID uuid);
}
