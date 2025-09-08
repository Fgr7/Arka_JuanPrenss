package com.arka.arkajuanprenss.infrastructure.adapter.out.persistence.mapper;

import com.arka.arkajuanprenss.domain.model.Category;
import com.arka.arkajuanprenss.infrastructure.adapter.out.persistence.entity.CategoryJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class CategoryPersistenceMapper {
    public CategoryJpaEntity toEntity(Category category) {
        if (category == null) {
            return null;
        }
        
        CategoryJpaEntity entity = new CategoryJpaEntity();
        entity.setId(category.getId());
        entity.setNombre(category.getNombre());
        return entity;
    }
    
    public Category toDomain(CategoryJpaEntity entity) {
        if (entity == null) {
            return null;
        }
        
        Category category = new Category();
        category.setId(entity.getId());
        category.setNombre(entity.getNombre());
        return category;
    }
}
