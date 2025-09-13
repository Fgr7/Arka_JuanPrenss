package com.arka.arkajuanprenss.infrastructure.adapter.out.persistence.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "orders")
public class OrderJpaEntity {
        @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relación con Customer
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private CustomerJpaEntity customer;

    // Relación con Product
    @ManyToMany
    @JoinTable(
        name = "order_products",
        joinColumns = @JoinColumn(name = "order_id"),
        inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<ProductJpaEntity> productos = new ArrayList<>();

    private LocalDateTime fechaCreacion;

    private String estado; // PENDIENTE, PAGADO, CANCELADO

    // ====== Constructores ======
    public OrderJpaEntity() {}

    public OrderJpaEntity(Long id, CustomerJpaEntity customer, List<ProductJpaEntity> productos,
                          LocalDateTime fechaCreacion, String estado) {
        this.id = id;
        this.customer = customer;
        this.productos = productos;
        this.fechaCreacion = fechaCreacion;
        this.estado = estado;
    }

    // ====== Getters y Setters ======
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public CustomerJpaEntity getCustomer() { return customer; }
    public void setCustomer(CustomerJpaEntity customer) { this.customer = customer; }

    public List<ProductJpaEntity> getProductos() { return productos; }
    public void setProductos(List<ProductJpaEntity> productos) { this.productos = productos; }

    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(LocalDateTime fechaCreacion) { this.fechaCreacion = fechaCreacion; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    // ====== Equals & HashCode ======
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderJpaEntity)) return false;
        OrderJpaEntity that = (OrderJpaEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
