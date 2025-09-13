package com.arka.arkajuanprenss.infrastructure.adapter.in.web.mapper;
import com.arka.arkajuanprenss.domain.model.Customer;
import com.arka.arkajuanprenss.domain.model.Order;
import com.arka.arkajuanprenss.domain.model.Product;
import com.arka.arkajuanprenss.infrastructure.adapter.in.web.dto.OrderDTO;

import java.util.List;
import java.util.stream.Collectors;

public class OrderWebMapper {
       // Domain → DTO
    public static OrderDTO toDTO(Order order) {
        OrderDTO dto = new OrderDTO();
        dto.setId(order.getId());
        dto.setCustomerId(order.getCustomer() != null ? order.getCustomer().getId() : null);
        dto.setProductIds(order.getProductos() != null
                ? order.getProductos().stream().map(Product::getId).collect(Collectors.toList())
                : null);
        dto.setFechaCreacion(order.getFechaCreacion());
        dto.setEstado(order.getEstado());
        return dto;
    }

    // DTO → Domain (solo IDs de relaciones, sin cargar objetos completos aún)
    public static Order toDomain(OrderDTO dto) {
        Order order = new Order();
        order.setId(dto.getId());

        if (dto.getCustomerId() != null) {
            Customer customer = new Customer();
            customer.setId(dto.getCustomerId());
            order.setCustomer(customer);
        }

        if (dto.getProductIds() != null) {
            List<Product> products = dto.getProductIds().stream()
                    .map(id -> {
                        Product product = new Product();
                        product.setId(id);
                        return product;
                    })
                    .collect(Collectors.toList());
            order.setProductos(products);
        }

        order.setFechaCreacion(dto.getFechaCreacion());
        order.setEstado(dto.getEstado());
        return order;
    }
}
