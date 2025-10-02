package com.arka.arkajuanprenss.infrastructure.adapter.out.persistence;

import com.arka.arkajuanprenss.domain.model.Cart;
import com.arka.arkajuanprenss.domain.port.out.CartRepositoryPort;
import com.arka.arkajuanprenss.infrastructure.adapter.out.persistence.entity.CartJpaEntity;
import com.arka.arkajuanprenss.infrastructure.adapter.out.persistence.mapper.CartMapper;
import com.arka.arkajuanprenss.infrastructure.adapter.out.persistence.repository.CartJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CartPersistenceAdapter implements CartRepositoryPort {
    private final CartJpaRepository cartJpaRepository;
    private final CartMapper cartMapper;

    public CartPersistenceAdapter(CartJpaRepository cartJpaRepository, CartMapper cartMapper) {
        this.cartJpaRepository = cartJpaRepository;
        this.cartMapper = cartMapper;
    }

    @Override
    public List<Cart> findAll() {
        return cartJpaRepository.findAll()
                .stream()
                .map(cartMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Cart> findById(Long id) {
        return cartJpaRepository.findById(id)
                .map(cartMapper::toDomain);
    }

    @Override
    public Cart save(Cart cart) {
        CartJpaEntity entity = cartMapper.toEntity(cart);

        // debug rápido: imprimir tamaño antes de guardar
        System.out.println("DEBUG: guardando carrito con productos.size = " + (entity.getProductos() == null ? 0 : entity.getProductos().size()));

        CartJpaEntity savedEntity = cartJpaRepository.saveAndFlush(entity); // fuerza flush inmediato
        return cartMapper.toDomain(savedEntity);
    }

    @Override
    public void deleteById(Long id) {
        cartJpaRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return cartJpaRepository.existsById(id);
    }
}
