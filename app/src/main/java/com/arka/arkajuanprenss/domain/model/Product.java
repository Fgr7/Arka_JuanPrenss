package com.arka.arkajuanprenss.domain.model;

import java.math.BigDecimal;

public class Product {
    private Long id;
    private String nombre;
    private String descripcion;
    private Category categoria;
    private String marca;
    private BigDecimal precioUnitario;
    private Integer stock;

    public Product() {}

    // Lógica de negocio pura
    public boolean hasValidPrice() {
        return precioUnitario != null && precioUnitario.compareTo(BigDecimal.ZERO) > 0;
    }

    public boolean isInStock() {
        return stock != null && stock > 0;
    }

    public boolean isAvailable() {
        return hasValidPrice() && isInStock();
    }

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public Category getCategoria() { return categoria; }
    public void setCategoria(Category categoria) { this.categoria = categoria; }

    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }

    public BigDecimal getPrecioUnitario() { return precioUnitario; }
    public void setPrecioUnitario(BigDecimal precioUnitario) { this.precioUnitario = precioUnitario; }

    public Integer getStock() { return stock; }
    public void setStock(Integer stock) { this.stock = stock; }
}
