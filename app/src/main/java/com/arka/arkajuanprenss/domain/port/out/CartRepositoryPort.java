package com.arka.arkajuanprenss.domain.port.out;
import com.arka.arkajuanprenss.domain.model.Cart;

import java.util.List;
import java.util.Optional;

public interface CartRepositoryPort {

    List<Cart> findAll();

    Optional<Cart> findById(Long id);

    Cart save(Cart cart);

    void deleteById(Long id);

    boolean existsById(Long id);
}
