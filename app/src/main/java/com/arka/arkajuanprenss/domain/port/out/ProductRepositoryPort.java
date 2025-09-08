package com.arka.arkajuanprenss.domain.port.out;

import com.arka.arkajuanprenss.domain.model.Product;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ProductRepositoryPort {
    List<Product> findAll();
    Optional<Product> findById(Long id);
    Product save(Product product);
    void deleteById(Long id);
    boolean existsById(Long id);
    List<Product> findByCategoriaNombre(String categoryName);
    List<Product> findByNombreContainingIgnoreCase(String name);
    List<Product> findByPriceRange(BigDecimal min, BigDecimal max);
}
