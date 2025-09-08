package com.arka.arkajuanprenss.application.usecase;

import com.arka.arkajuanprenss.domain.model.Category;
import com.arka.arkajuanprenss.domain.port.in.CategoryUseCase;
import com.arka.arkajuanprenss.domain.port.out.CategoryRepositoryPort;
import com.arka.arkajuanprenss.domain.port.out.DomainEventPublisher;
import com.arka.arkajuanprenss.domain.event.CategoryCreatedEvent;

import java.util.List;

public class CategoryApplicationService implements CategoryUseCase {
    private final CategoryRepositoryPort categoryRepository;
    private final DomainEventPublisher eventPublisher; 

    public CategoryApplicationService(CategoryRepositoryPort categoryRepository,
                                      DomainEventPublisher eventPublisher) {
        this.categoryRepository = categoryRepository;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
    }

    @Override
    public Category createCategory(Category category) {
        validateCategory(category);
        Category saved = categoryRepository.save(category);

        // 🔹 Publicamos el evento de dominio
        CategoryCreatedEvent event = new CategoryCreatedEvent(saved.getId(), saved.getNombre());
        eventPublisher.publish(event);

        return saved;
    }

    @Override
    public Category updateCategory(Long id, Category category) {
        if (!categoryRepository.existsById(id)) {
            throw new RuntimeException("Category not found with id: " + id);
        }
        validateCategory(category);
        category.setId(id);
        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new RuntimeException("Category not found with id: " + id);
        }
        categoryRepository.deleteById(id);
    }

    private void validateCategory(Category category) {
        if (category == null) {
            throw new IllegalArgumentException("Category cannot be null");
        }

        if (category.getNombre() == null || category.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("Category name cannot be null or empty");
        }

        if (category.getNombre().length() > 100) {
            throw new IllegalArgumentException("Category name cannot exceed 100 characters");
        }
    }
}
