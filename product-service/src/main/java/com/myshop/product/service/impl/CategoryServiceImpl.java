package com.myshop.product.service.impl;

import com.myshop.product.dto.CategoryDto;
import com.myshop.product.entity.Category;
import com.myshop.product.exception.ResourceNotFoundException;
import com.myshop.product.mapper.CategoryMapper;
import com.myshop.product.repository.CategoryRepository;
import com.myshop.product.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public CategoryDto create(CategoryDto categoryDto) {
        Category category = categoryMapper.toEntity(categoryDto);
        categoryRepository.save(category);
        return categoryMapper.toDto(category);
    }

    @Override
    public CategoryDto update(UUID uuid, CategoryDto categoryDto) {
        Category existingCategory = categoryRepository.findById(uuid)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Category not found with id : " + uuid
                        ));

        //update all fields
        existingCategory.setTitle(categoryDto.getTitle());

        Category updatedCategory = categoryRepository.save(existingCategory);

        return categoryMapper.toDto(updatedCategory);
    }

    @Override
    public boolean delete(UUID uuid) {

        Category category = categoryRepository.findById(uuid)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Category not found with id : " + uuid
                        ));

        categoryRepository.delete(category);

        return true;
    }

    @Override
    public List<CategoryDto> getAllCategory() {
        List<Category> categoryList = categoryRepository.findAll();

        return categoryList.stream()
                .map(categoryMapper::toDto)
                .toList();
    }

    @Override
    public CategoryDto getCategoryById(UUID uuid) {
        Category category = categoryRepository.findById(uuid)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Category not found with id : " + uuid
                        ));

        return categoryMapper.toDto(category);

    }
}
