package com.arka.arkajuanprenss.infrastructure.adapter.out.persistence.repository;

import com.arka.arkajuanprenss.infrastructure.adapter.out.persistence.entity.ProductJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductJpaRepository extends JpaRepository <ProductJpaEntity, Long>{
    // 1. Consulta derivada de nombre de propiedad
    List<ProductJpaEntity> findByCategoriaNombre(String categoryName);
    
    // 2. Consulta derivada con "containing" e ignore case
    List<ProductJpaEntity> findByNombreContainingIgnoreCase(String name);
    
    // 3. Consulta usando @Query con JPQL
    @Query("SELECT p FROM ProductJpaEntity p WHERE p.precioUnitario BETWEEN :min AND :max")
    List<ProductJpaEntity> findByPriceRange(@Param("min") BigDecimal min, 
                                            @Param("max") BigDecimal max);
    
}
