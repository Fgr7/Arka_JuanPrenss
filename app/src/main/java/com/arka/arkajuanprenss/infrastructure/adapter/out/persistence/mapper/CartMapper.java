package com.arka.arkajuanprenss.infrastructure.adapter.out.persistence.mapper;

import com.arka.arkajuanprenss.domain.model.Cart;
import com.arka.arkajuanprenss.domain.model.Customer;
import com.arka.arkajuanprenss.domain.model.Product;
import com.arka.arkajuanprenss.infrastructure.adapter.out.persistence.entity.CartJpaEntity;
import com.arka.arkajuanprenss.infrastructure.adapter.out.persistence.entity.CustomerJpaEntity;
import com.arka.arkajuanprenss.infrastructure.adapter.out.persistence.entity.ProductJpaEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CartMapper {

   @PersistenceContext
    private EntityManager entityManager;

    public CartJpaEntity toEntity(Cart cart) {
        if (cart == null) return null;

        CustomerJpaEntity customerEntity = null;
        if (cart.getCustomer() != null && cart.getCustomer().getId() != null) {
            customerEntity = entityManager.getReference(CustomerJpaEntity.class, cart.getCustomer().getId());
        }

        List<ProductJpaEntity> productEntities = new ArrayList<>();
        if (cart.getProductos() != null) {
            for (Product p : cart.getProductos()) {
                if (p != null && p.getId() != null) {
                    ProductJpaEntity ref = entityManager.getReference(ProductJpaEntity.class, p.getId());
                    productEntities.add(ref);
                }
            }
        }

        return new CartJpaEntity(
                cart.getId(),
                customerEntity,
                productEntities
        );
    }

    public Cart toDomain(CartJpaEntity entity) {
        if (entity == null) return null;

        Customer customer = null;
        if (entity.getCustomer() != null) {
            customer = new Customer();
            customer.setId(entity.getCustomer().getId());
        }

        List<Product> products = new ArrayList<>();
        if (entity.getProductos() != null) {
            for (ProductJpaEntity p : entity.getProductos()) {
                Product product = new Product();
                product.setId(p.getId());
                product.setNombre(p.getNombre()); // opcional, según lo quieras devolver
                products.add(product);
            }
        }

        return new Cart(entity.getId(), customer, products);
    }
}
