package com.arka.arkajuanprenss.infrastructure.adapter.out.persistence.repository;
import com.arka.arkajuanprenss.infrastructure.adapter.out.persistence.entity.CartJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartJpaRepository extends JpaRepository<CartJpaEntity, Long>{
    // Buscar carritos por cliente
    List<CartJpaEntity> findByCustomer_Id(Long customerId);

    // Buscar carritos que contengan un producto específico
    List<CartJpaEntity> findByProductos_Id(Long productId);
}
