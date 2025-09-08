package com.arka.arkajuanprenss.infrastructure.adapter.in.web.mapper;
import com.arka.arkajuanprenss.domain.model.Category;
import com.arka.arkajuanprenss.domain.model.Product;
import com.arka.arkajuanprenss.domain.port.out.CategoryRepositoryPort;
import com.arka.arkajuanprenss.infrastructure.adapter.in.web.dto.ProductDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductWebMapper {
        private final CategoryRepositoryPort categoryRepository;

    public ProductWebMapper(CategoryRepositoryPort categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    
    public ProductDTO toDTO(Product product) {
        if (product == null) {
            return null;
        }
        
        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setNombre(product.getNombre());
        dto.setDescripcion(product.getDescripcion());
        dto.setCategoriaId(product.getCategoria() != null ? product.getCategoria().getId() : null);
        dto.setMarca(product.getMarca());
        dto.setPrecioUnitario(product.getPrecioUnitario());
        dto.setStock(product.getStock());
        return dto;
    }
    
    public Product toDomain(ProductDTO dto) {
        if (dto == null) {
            return null;
        }
        
        Product product = new Product();
        product.setId(dto.getId());
        product.setNombre(dto.getNombre());
        product.setDescripcion(dto.getDescripcion());
        product.setMarca(dto.getMarca());
        product.setPrecioUnitario(dto.getPrecioUnitario());
        product.setStock(dto.getStock());
        
        // Cargar la categoría completa si se proporciona el ID
        if (dto.getCategoriaId() != null) {
            Category categoria = categoryRepository.findById(dto.getCategoriaId())
                    .orElseThrow(() -> new RuntimeException("Category not found with id: " + dto.getCategoriaId()));
            product.setCategoria(categoria);
        }
        
        return product;
    }
    
    public List<ProductDTO> toDTO(List<Product> products) {
        return products.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
