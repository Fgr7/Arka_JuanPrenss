package com.arka.arkajuanprenss.infrastructure.adapter.out.persistence;

import com.arka.arkajuanprenss.infrastructure.adapter.out.persistence.mapper.OrderMapper;
import com.arka.arkajuanprenss.domain.model.Order;
import com.arka.arkajuanprenss.domain.port.out.OrderRepositoryPort;
import com.arka.arkajuanprenss.infrastructure.adapter.out.persistence.entity.OrderJpaEntity;
import com.arka.arkajuanprenss.infrastructure.adapter.out.persistence.repository.OrderJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class OrderPersistenceAdapter implements OrderRepositoryPort {
    private final OrderJpaRepository orderJpaRepository;

    public OrderPersistenceAdapter(OrderJpaRepository orderJpaRepository) {
        this.orderJpaRepository = orderJpaRepository;
    }

    @Override
    public List<Order> findAll() {
        return orderJpaRepository.findAll()
                .stream()
                .map(OrderMapper::toDomain) // usamos el mapper
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Order> findById(Long id) {
        return orderJpaRepository.findById(id)
                .map(OrderMapper::toDomain);
    }

    @Override
    public Order save(Order order) {
        OrderJpaEntity entity = OrderMapper.toEntity(order);
        OrderJpaEntity savedEntity = orderJpaRepository.save(entity);
        return OrderMapper.toDomain(savedEntity);
    }

    @Override
    public void deleteById(Long id) {
        orderJpaRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return orderJpaRepository.existsById(id);
    }

    // Métodos custom conectados al JpaRepository
    public List<Order> findByEstado(String estado) {
        return orderJpaRepository.findByEstado(estado)
                .stream()
                .map(OrderMapper::toDomain)
                .collect(Collectors.toList());
    }

    public List<Order> findByFechaCreacionAfter(java.time.LocalDateTime fecha) {
        return orderJpaRepository.findByFechaCreacionAfter(fecha)
                .stream()
                .map(OrderMapper::toDomain)
                .collect(Collectors.toList());
    }
}
