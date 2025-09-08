package com.arka.arkajuanprenss.infrastructure.adapter.out.persistence;

import com.arka.arkajuanprenss.domain.model.Category;
import com.arka.arkajuanprenss.domain.port.out.CategoryRepositoryPort;
import com.arka.arkajuanprenss.infrastructure.adapter.out.persistence.entity.CategoryJpaEntity;
import com.arka.arkajuanprenss.infrastructure.adapter.out.persistence.mapper.CategoryPersistenceMapper;
import com.arka.arkajuanprenss.infrastructure.adapter.out.persistence.repository.CategoryJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CategoryPersistenceAdapter implements CategoryRepositoryPort{
    private final CategoryJpaRepository jpaRepository;
    private final CategoryPersistenceMapper mapper;

    public CategoryPersistenceAdapter(CategoryJpaRepository jpaRepository, 
                                    CategoryPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public List<Category> findAll() {
        List<CategoryJpaEntity> entities = jpaRepository.findAll();
        return entities.stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Category> findById(Long id) {
        return jpaRepository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public Category save(Category category) {
        CategoryJpaEntity entity = mapper.toEntity(category);
        CategoryJpaEntity savedEntity = jpaRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return jpaRepository.existsById(id);
    }
    
}
