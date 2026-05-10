package com.myshop.product.controller;

import com.myshop.product.dto.ProductDto;
import com.myshop.product.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/products")
public class ProductController {

    @Autowired
    private ProductService productService;


    // create product
    @PostMapping("/category/{categoryId}")
    public ResponseEntity<ProductDto> createProduct(
            @Valid @RequestBody ProductDto productDto,
            @PathVariable UUID categoryId

    ) {

        ProductDto createdProduct = productService.create(productDto, categoryId);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }


    // update product
    @PutMapping("/{productId}")
    public ResponseEntity<ProductDto> updateProduct(
            @PathVariable UUID productId,
            @Valid @RequestBody ProductDto productDto
    ) {

        ProductDto updatedProduct = productService.update(productId, productDto);
        return ResponseEntity.ok(updatedProduct);
    }


    // delete product
    @DeleteMapping("/{productId}")
    public ResponseEntity<String> deleteProduct(
            @PathVariable UUID productId
    ) {

        productService.delete(productId);
        return ResponseEntity.ok("Product deleted successfully !!");
    }


    // get all products
    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts() {

        List<ProductDto> productList = productService.getAllProduct();
        return ResponseEntity.ok(productList);
    }


    // get single product
    @GetMapping("/{productId}")
    public ResponseEntity<ProductDto> getProductById(
            @PathVariable UUID productId
    ) {

        ProductDto productDto = productService.getProductById(productId);
        return ResponseEntity.ok(productDto);
    }
}
