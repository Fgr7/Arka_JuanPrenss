package com.arka.arkajuanprenss.infrastructure.adapter.out.persistence.mapper;

import com.arka.arkajuanprenss.domain.model.Customer;
import com.arka.arkajuanprenss.domain.model.Order;
import com.arka.arkajuanprenss.domain.model.Product;
import com.arka.arkajuanprenss.infrastructure.adapter.out.persistence.entity.CustomerJpaEntity;
import com.arka.arkajuanprenss.infrastructure.adapter.out.persistence.entity.OrderJpaEntity;
import com.arka.arkajuanprenss.infrastructure.adapter.out.persistence.entity.ProductJpaEntity;

import java.util.List;
import java.util.stream.Collectors;

public class OrderMapper {

    // ===== De dominio a JPA =====
    public static OrderJpaEntity toEntity(Order order) {
        if (order == null) return null;

        return new OrderJpaEntity(
                order.getId(),
                toCustomerEntity(order.getCustomer()),
                toProductEntities(order.getProductos()),
                order.getFechaCreacion(),
                order.getEstado()
        );
    }

    // ===== De JPA a dominio =====
    public static Order toDomain(OrderJpaEntity entity) {
        if (entity == null) return null;

        Order order = new Order();
        order.setId(entity.getId());
        order.setCustomer(toCustomerDomain(entity.getCustomer()));
        order.setProductos(toProductDomainList(entity.getProductos()));
        order.setFechaCreacion(entity.getFechaCreacion());
        order.setEstado(entity.getEstado());
        return order;
    }

    // ===== Helpers Customer =====
    private static CustomerJpaEntity toCustomerEntity(Customer customer) {
        if (customer == null) return null;
        return new CustomerJpaEntity(
                customer.getId(),
                customer.getNombre(),
                customer.getEmail(),
                customer.getTelefono(),
                customer.getCiudad(),
                customer.getPais()
        );
    }

    private static Customer toCustomerDomain(CustomerJpaEntity entity) {
        if (entity == null) return null;
        Customer customer = new Customer();
        customer.setId(entity.getId());
        customer.setNombre(entity.getNombre());
        customer.setEmail(entity.getEmail());
        customer.setTelefono(entity.getTelefono());
        customer.setCiudad(entity.getCiudad());
        customer.setPais(entity.getPais());
        return customer;
    }

    // ===== Helpers Product =====
    private static List<ProductJpaEntity> toProductEntities(List<Product> productos) {
        if (productos == null) return null;
        return productos.stream()
                .map(p -> {
                    ProductJpaEntity entity = new ProductJpaEntity();
                    entity.setId(p.getId());
                    entity.setNombre(p.getNombre());
                    entity.setDescripcion(p.getDescripcion());
                    entity.setMarca(p.getMarca());
                    entity.setPrecioUnitario(p.getPrecioUnitario());
                    entity.setStock(p.getStock());
                    // Ojo: aquí falta el mapeo de Category (categoria),
                    // pero eso requiere tener un CategoryMapper
                    return entity;
                })
                .collect(Collectors.toList());
    }

    private static List<Product> toProductDomainList(List<ProductJpaEntity> entities) {
        if (entities == null) return null;
        return entities.stream()
                .map(e -> {
                    Product product = new Product();
                    product.setId(e.getId());
                    product.setNombre(e.getNombre());
                    product.setDescripcion(e.getDescripcion());
                    product.setMarca(e.getMarca());
                    product.setPrecioUnitario(e.getPrecioUnitario());
                    product.setStock(e.getStock());
                    // Falta categoria → se hace con CategoryMapper
                    return product;
                })
                .collect(Collectors.toList());
    }
}
