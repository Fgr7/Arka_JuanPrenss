package com.arka.arkajuanprenss.infrastructure.adapter.in.web.mapper;
import com.arka.arkajuanprenss.domain.model.Category;
import com.arka.arkajuanprenss.infrastructure.adapter.in.web.dto.CategoryDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoryWebMapper {
        public CategoryDTO toDTO(Category category) {
        if (category == null) {
            return null;
        }
        return new CategoryDTO(category.getId(), category.getNombre());
    }
    
    public Category toDomain(CategoryDTO dto) {
        if (dto == null) {
            return null;
        }
        
        Category category = new Category();
        category.setId(dto.getId());
        category.setNombre(dto.getNombre());
        return category;
    }
    
    public List<CategoryDTO> toDTO(List<Category> categories) {
        return categories.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
            }
    
}
