package com.arka.arkajuanprenss.infrastructure.adapter.out.persistence.repository;

import com.arka.arkajuanprenss.infrastructure.adapter.out.persistence.entity.OrderJpaEntity;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrderJpaRepository extends JpaRepository<OrderJpaEntity, Long> {
    // 1) Buscar todas las órdenes por estado
    List<OrderJpaEntity> findByEstado(String estado);

    // 2) Buscar todas las órdenes creadas después de una fecha dada
    List<OrderJpaEntity> findByFechaCreacionAfter(LocalDateTime fecha);
}
