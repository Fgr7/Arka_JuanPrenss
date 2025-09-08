package com.arka.arkajuanprenss.infrastructure.adapter.in.web;
import  com.arka.arkajuanprenss.domain.model.Category;
import  com.arka.arkajuanprenss.domain.port.in.CategoryUseCase;
import  com.arka.arkajuanprenss.infrastructure.adapter.in.web.dto.CategoryDTO;
import  com.arka.arkajuanprenss.infrastructure.adapter.in.web.mapper.CategoryWebMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoryController {
        
    private final CategoryUseCase categoryUseCase;
    private final CategoryWebMapper webMapper;

    public CategoryController(CategoryUseCase categoryUseCase, CategoryWebMapper webMapper) {
        this.categoryUseCase = categoryUseCase;
        this.webMapper = webMapper;
    }

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        List<Category> categories = categoryUseCase.getAllCategories();
        List<CategoryDTO> categoriesDTO = webMapper.toDTO(categories);
        return ResponseEntity.ok(categoriesDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Long id) {
        Category category = categoryUseCase.getCategoryById(id);
        CategoryDTO categoryDTO = webMapper.toDTO(category);
        return ResponseEntity.ok(categoryDTO);
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO categoryDTO) {
        Category category = webMapper.toDomain(categoryDTO);
        Category savedCategory = categoryUseCase.createCategory(category);
        CategoryDTO savedCategoryDTO = webMapper.toDTO(savedCategory);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCategoryDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable Long id, 
                                                    @RequestBody CategoryDTO categoryDTO) {
        Category category = webMapper.toDomain(categoryDTO);
        Category updatedCategory = categoryUseCase.updateCategory(id, category);
        CategoryDTO updatedCategoryDTO = webMapper.toDTO(updatedCategory);
        return ResponseEntity.ok(updatedCategoryDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryUseCase.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}
