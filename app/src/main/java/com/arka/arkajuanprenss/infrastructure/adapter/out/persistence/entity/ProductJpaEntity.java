package com.arka.arkajuanprenss.infrastructure.adapter.out.persistence.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "productos")
public class ProductJpaEntity {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "producto_id")
    private Long id;

    @Column(name = "nombre", nullable = false, length = 255)
    private String nombre;

    @Column(name = "descripcion", length = 500)
    private String descripcion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id")
    private CategoryJpaEntity categoria;

    @Column(name = "marca", length = 255)
    private String marca;

    @Column(name = "precio_unitario", precision = 12, scale = 2)
    private BigDecimal precioUnitario;

    @Column(name = "stock")
    private Integer stock;

    // 🔑 Relación inversa con carrito
    @ManyToMany(mappedBy = "productos")
    private List<CartJpaEntity> carritos;

    // ===== Constructores =====
    public ProductJpaEntity() {}

    // ===== Getters & Setters =====
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public CategoryJpaEntity getCategoria() { return categoria; }
    public void setCategoria(CategoryJpaEntity categoria) { this.categoria = categoria; }

    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }

    public BigDecimal getPrecioUnitario() { return precioUnitario; }
    public void setPrecioUnitario(BigDecimal precioUnitario) { this.precioUnitario = precioUnitario; }

    public Integer getStock() { return stock; }
    public void setStock(Integer stock) { this.stock = stock; }

    public List<CartJpaEntity> getCarritos() { return carritos; }
    public void setCarritos(List<CartJpaEntity> carritos) { this.carritos = carritos; }
}
