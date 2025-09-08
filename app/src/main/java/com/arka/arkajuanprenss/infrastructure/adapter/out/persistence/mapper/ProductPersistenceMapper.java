package com.arka.arkajuanprenss.infrastructure.adapter.out.persistence.mapper;

import com.arka.arkajuanprenss.domain.model.Product;
import com.arka.arkajuanprenss.infrastructure.adapter.out.persistence.entity.ProductJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class ProductPersistenceMapper {
    private final CategoryPersistenceMapper categoryMapper;

    public ProductPersistenceMapper(CategoryPersistenceMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }
    
    public ProductJpaEntity toEntity(Product product) {
        if (product == null) {
            return null;
        }
        
        ProductJpaEntity entity = new ProductJpaEntity();
        entity.setId(product.getId());
        entity.setNombre(product.getNombre());
        entity.setDescripcion(product.getDescripcion());
        entity.setCategoria(categoryMapper.toEntity(product.getCategoria()));
        entity.setMarca(product.getMarca());
        entity.setPrecioUnitario(product.getPrecioUnitario());
        entity.setStock(product.getStock());
        return entity;
    }
    
    public Product toDomain(ProductJpaEntity entity) {
        if (entity == null) {
            return null;
        }
        
        Product product = new Product();
        product.setId(entity.getId());
        product.setNombre(entity.getNombre());
        product.setDescripcion(entity.getDescripcion());
        product.setCategoria(categoryMapper.toDomain(entity.getCategoria()));
        product.setMarca(entity.getMarca());
        product.setPrecioUnitario(entity.getPrecioUnitario());
        product.setStock(entity.getStock());
        return product;
    }
    
}
