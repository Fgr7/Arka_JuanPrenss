package com.arka.arkajuanprenss.infrastructure.adapter.out.persistence.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "carritos")
public class CartJpaEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "carrito_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    private CustomerJpaEntity customer;

    @ManyToMany
    @JoinTable(
        name = "carrito_productos",
        joinColumns = @JoinColumn(name = "carrito_id"),
        inverseJoinColumns = @JoinColumn(name = "producto_id")
    )
    private List<ProductJpaEntity> productos = new ArrayList<>(); // <-- inicializada

    public CartJpaEntity() {
        this.productos = new ArrayList<>();
    }

    public CartJpaEntity(Long id, CustomerJpaEntity customer, List<ProductJpaEntity> productos) {
        this.id = id;
        this.customer = customer;
        this.productos = productos != null ? productos : new ArrayList<>();
    }

    // ===== Getters & Setters =====
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public CustomerJpaEntity getCustomer() { return customer; }
    public void setCustomer(CustomerJpaEntity customer) { this.customer = customer; }

    public List<ProductJpaEntity> getProductos() { return productos; }
    public void setProductos(List<ProductJpaEntity> productos) { this.productos = productos; }
}
