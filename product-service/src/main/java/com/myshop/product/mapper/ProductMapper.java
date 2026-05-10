package com.myshop.product.mapper;

import com.myshop.product.dto.ProductDto;
import com.myshop.product.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    // dto to entity
    public Product toEntity(ProductDto productDto) {

        return Product.builder()
                .uuid(productDto.getUuid())
                .title(productDto.getTitle())
                .shortDesc(productDto.getShortDesc())
                .longDesc(productDto.getLongDesc())
                .price(productDto.getPrice())
                .discount(productDto.getDiscount())
                .live(productDto.getLive())
                .build();
    }

    // entity to dto
    public ProductDto toDto(Product product) {

        return ProductDto.builder()
                .uuid(product.getUuid())
                .title(product.getTitle())
                .shortDesc(product.getShortDesc())
                .longDesc(product.getLongDesc())
                .price(product.getPrice())
                .discount(product.getDiscount())
                .live(product.getLive())
                .categoryTitle(product.getCategory().getTitle())
                .build();
    }
}
