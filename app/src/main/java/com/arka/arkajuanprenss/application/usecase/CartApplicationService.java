package com.arka.arkajuanprenss.application.usecase;
import com.arka.arkajuanprenss.domain.model.Cart;
import com.arka.arkajuanprenss.domain.model.Product;
import com.arka.arkajuanprenss.domain.port.in.CartUseCase;
import com.arka.arkajuanprenss.domain.port.out.CartRepositoryPort;
import com.arka.arkajuanprenss.domain.port.out.ProductRepositoryPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartApplicationService implements CartUseCase {
        private final CartRepositoryPort cartRepository;
    private final ProductRepositoryPort productRepository;

    public CartApplicationService(CartRepositoryPort cartRepository,
                                  ProductRepositoryPort productRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    @Override
    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }

    @Override
    public Cart getCartById(Long id) {
        return cartRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cart not found with id: " + id));
    }

    @Override
    public Cart createCart(Cart cart) {
        // Resolver los productos a partir de los IDs
        if (cart.getProductos() != null) {
            List<Product> resolvedProducts = cart.getProductos().stream()
                .map(p -> productRepository.findById(p.getId())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + p.getId())))
                .collect(Collectors.toList());
            cart.setProductos(resolvedProducts);
        }

        return cartRepository.save(cart);
    }

    @Override
    public Cart updateCart(Long id, Cart cart) {
        if (!cartRepository.existsById(id)) {
            throw new RuntimeException("Cart not found with id: " + id);
        }
        validateCart(cart);
        cart.setId(id);
        return cartRepository.save(cart);
    }

    @Override
    public void deleteCart(Long id) {
        if (!cartRepository.existsById(id)) {
            throw new RuntimeException("Cart not found with id: " + id);
        }
        cartRepository.deleteById(id);
    }


    @Transactional
    @Override
    public Cart addProductToCart(Long cartId, Long productId) {
        Cart cart = getCartById(cartId);
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + productId));

        if (cart.getProductos() == null) {
            cart.setProductos(new ArrayList<>());
        }
        cart.agregarProducto(product);
        return cartRepository.save(cart);
    }

    @Override
    public Cart removeProductFromCart(Long cartId, Long productId) {
        Cart cart = getCartById(cartId);
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + productId));

        cart.removerProducto(product);
        return cartRepository.save(cart);
    }

    @Override
    public void clearCart(Long cartId) {
        Cart cart = getCartById(cartId);
        cart.getProductos().clear();
        cartRepository.save(cart);
    }

    // ===== Validaciones =====
    private void validateCart(Cart cart) {
        if (cart == null) {
            throw new IllegalArgumentException("Cart cannot be null");
        }
        if (cart.getCustomer() == null || cart.getCustomer().getId() == null) {
            throw new IllegalArgumentException("Cart must be associated with a valid customer");
        }
    }
}
