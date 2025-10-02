package com.arka.arkajuanprenss.infrastructure.adapter.in.web;
import com.arka.arkajuanprenss.domain.port.in.CartUseCase;
import com.arka.arkajuanprenss.infrastructure.adapter.in.web.dto.CartDTO;
import com.arka.arkajuanprenss.infrastructure.adapter.in.web.mapper.CartWebMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/carts")
public class CartController {
        private final CartUseCase cartUseCase;

    public CartController(CartUseCase cartUseCase) {
        this.cartUseCase = cartUseCase;
    }

    @GetMapping
    public ResponseEntity<List<CartDTO>> getAllCarts() {
        List<CartDTO> carts = cartUseCase.getAllCarts().stream()
                .map(CartWebMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(carts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartDTO> getCartById(@PathVariable Long id) {
        return ResponseEntity.ok(CartWebMapper.toDTO(cartUseCase.getCartById(id)));
    }

    @PostMapping
    public ResponseEntity<CartDTO> createCart(@RequestBody CartDTO cartDTO) {
        return ResponseEntity.ok(
                CartWebMapper.toDTO(cartUseCase.createCart(CartWebMapper.toDomain(cartDTO)))
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<CartDTO> updateCart(@PathVariable Long id, @RequestBody CartDTO cartDTO) {
        return ResponseEntity.ok(
                CartWebMapper.toDTO(cartUseCase.updateCart(id, CartWebMapper.toDomain(cartDTO)))
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCart(@PathVariable Long id) {
        cartUseCase.deleteCart(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{cartId}/products/{productId}")
    public ResponseEntity<CartDTO> addProductToCart(@PathVariable Long cartId, @PathVariable Long productId) {
        return ResponseEntity.ok(
                CartWebMapper.toDTO(cartUseCase.addProductToCart(cartId, productId))
        );
    }

    @DeleteMapping("/{cartId}/products/{productId}")
    public ResponseEntity<CartDTO> removeProductFromCart(@PathVariable Long cartId, @PathVariable Long productId) {
        return ResponseEntity.ok(
                CartWebMapper.toDTO(cartUseCase.removeProductFromCart(cartId, productId))
        );
    }

    @DeleteMapping("/{cartId}/clear")
    public ResponseEntity<CartDTO> clearCart(@PathVariable Long cartId) {
        cartUseCase.clearCart(cartId);
        return ResponseEntity.noContent().build();
    }
}
