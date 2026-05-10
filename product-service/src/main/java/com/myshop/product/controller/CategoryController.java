package com.myshop.product.controller;

import com.myshop.product.dto.CategoryDto;
import com.myshop.product.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    // create category
    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(
            @RequestBody CategoryDto categoryDto
    ) {

        CategoryDto createdCategory = categoryService.create(categoryDto);
        return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
    }


    // update category
    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory(
            @PathVariable UUID categoryId,
            @Valid @RequestBody CategoryDto categoryDto
    ) {

        CategoryDto updatedCategory = categoryService.update(categoryId, categoryDto);
        return ResponseEntity.ok(updatedCategory);
    }


    // delete category
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<String> deleteCategory(
            @PathVariable UUID categoryId
    ) {

        categoryService.delete(categoryId);
        return ResponseEntity.ok("Category deleted successfully !!");
    }


    // get all categories
    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategories() {

        List<CategoryDto> categoryList = categoryService.getAllCategory();
        return ResponseEntity.ok(categoryList);
    }


    // get single category
    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> getCategoryById(
            @PathVariable UUID categoryId
    ) {

        CategoryDto categoryDto = categoryService.getCategoryById(categoryId);
        return ResponseEntity.ok(categoryDto);
    }
}