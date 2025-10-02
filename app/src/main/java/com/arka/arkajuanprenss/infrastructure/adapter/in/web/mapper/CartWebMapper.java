package com.arka.arkajuanprenss.infrastructure.adapter.in.web.mapper;

import com.arka.arkajuanprenss.domain.model.Cart;
import com.arka.arkajuanprenss.domain.model.Customer;
import com.arka.arkajuanprenss.domain.model.Product;
import com.arka.arkajuanprenss.infrastructure.adapter.in.web.dto.CartDTO;

import java.util.List;
import java.util.stream.Collectors;

public class CartWebMapper {
    // Domain -> DTO
    public static CartDTO toDTO(Cart cart) {
        return new CartDTO(
                cart.getId(),
                cart.getCustomer() != null ? cart.getCustomer().getId() : null,
                cart.getProductos() != null
                        ? cart.getProductos().stream().map(Product::getId).collect(Collectors.toList())
                        : null
        );
    }

    // DTO -> Domain
    public static Cart toDomain(CartDTO dto) {
        Cart cart = new Cart();

        if (dto.getId() != null) {
            cart.setId(dto.getId());
        }

        // Mapear Customer
        if (dto.getCustomerId() != null) {
            Customer customer = new Customer();
            customer.setId(dto.getCustomerId());
            cart.setCustomer(customer);
        }

        // Mapear Productos
        if (dto.getProductIds() != null) {
            List<Product> productos = dto.getProductIds().stream()
                    .map(pid -> {
                        Product p = new Product();
                        p.setId(pid); // solo setea el ID
                        return p;
                    })
                    .collect(Collectors.toList());
            cart.setProductos(productos);
        }

        return cart;
    }
}
