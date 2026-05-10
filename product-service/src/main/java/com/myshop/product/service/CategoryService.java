package com.myshop.product.service;

import com.myshop.product.dto.CategoryDto;


import java.util.List;
import java.util.UUID;

public interface CategoryService {

    //create
    CategoryDto create(CategoryDto categoryDto);

    //update
    CategoryDto update(UUID uuid, CategoryDto categoryDto);

    //delete
    boolean delete(UUID uuid);

    //get all
    List<CategoryDto> getAllCategory();

    //get single by id
    CategoryDto getCategoryById(UUID uuid);

}
