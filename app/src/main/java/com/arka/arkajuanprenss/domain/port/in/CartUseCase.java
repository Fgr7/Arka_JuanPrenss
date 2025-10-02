package com.arka.arkajuanprenss.domain.port.in;
import com.arka.arkajuanprenss.domain.model.Cart;

import java.util.List;

public interface CartUseCase {

    List<Cart> getAllCarts();

    Cart getCartById(Long id);

    Cart createCart(Cart cart);

    Cart updateCart(Long id, Cart cart);

    void deleteCart(Long id);

    // Métodos específicos del carrito
    Cart addProductToCart(Long cartId, Long productId);

    Cart removeProductFromCart(Long cartId, Long productId);

    void clearCart(Long cartId);
}
