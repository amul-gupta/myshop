package com.myshop.product.service.impl;

import com.myshop.product.dto.ProductDto;
import com.myshop.product.entity.Category;
import com.myshop.product.entity.Product;
import com.myshop.product.exception.ResourceNotFoundException;
import com.myshop.product.mapper.ProductMapper;
import com.myshop.product.repository.CategoryRepository;
import com.myshop.product.repository.ProductRepository;
import com.myshop.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public ProductDto create(ProductDto productDto, UUID categoryId) {

        Category category = categoryRepository
                .findById(categoryId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Category not found"
                        ));

        Product product = productMapper.toEntity(productDto);
        product.setCategory(category);
        Product savedProduct = productRepository.save(product);
        return productMapper.toDto(savedProduct);
    }

    @Override
    public ProductDto update(UUID uuid, ProductDto productDto) {

        Product existingProduct = productRepository.findById(uuid)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Product not found with id : " + uuid
                        ));

        //update all fields here

        Product updatedProduct = productRepository.save(existingProduct);
        return productMapper.toDto(updatedProduct);
    }

    @Override
    public boolean delete(UUID uuid) {

        Product product = productRepository.findById(uuid)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Product not found with id : " + uuid
                        ));

        productRepository.delete(product);

        return true;
    }

    @Override
    public List<ProductDto> getAllProduct() {

        List<Product> productList = productRepository.findAll();

        return productList.stream()
                .map(productMapper::toDto)
                .toList();
    }

    @Override
    public ProductDto getProductById(UUID uuid) {

        Product product = productRepository.findById(uuid)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Product not found with id : " + uuid
                        ));

        return productMapper.toDto(product);
    }

    @Override
    public List<ProductDto> getProductByCategoryId(UUID uuid) {

        List<Product> productList =
                productRepository.findByCategoryUuid(uuid);

        return productList.stream()
                .map(productMapper::toDto)
                .toList();
    }

}
