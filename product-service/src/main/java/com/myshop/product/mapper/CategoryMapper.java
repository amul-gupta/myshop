package com.myshop.product.mapper;

import com.myshop.product.dto.CategoryDto;
import com.myshop.product.entity.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    //dto to entity
    public Category toEntity(CategoryDto categoryDto)
    {
        return Category.builder()
                .id(categoryDto.getId())
                .title(categoryDto.getTitle())
                .build();
    }


    //entity to dto
    public CategoryDto toDto(Category category)
    {
        return CategoryDto.builder()
                .id(category.getId())
                .title(category.getTitle())
                .build();
    }
}
