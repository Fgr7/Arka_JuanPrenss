package com.arka.arkajuanprenss.domain.model;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class Order {
    private Long id;
    private Customer customer;             // Relación con cliente
    private List<Product> productos;       // Relación con productos
    private LocalDateTime fechaCreacion;
    private String estado;                 // Ej: "PENDIENTE", "PAGADO", "CANCELADO"

    public Order() {
        this.productos = new ArrayList<>();
        this.fechaCreacion = LocalDateTime.now();
        this.estado = "PENDIENTE";
    }

    // ===== Lógica de negocio =====
    public BigDecimal calcularTotal() {
        return productos.stream()
                .map(Product::getPrecioUnitario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void agregarProducto(Product producto) {
        if (producto != null && producto.isAvailable()) {
            this.productos.add(producto);
        } else {
            throw new RuntimeException("No se puede agregar un producto no disponible");
        }
    }

    public void removerProducto(Product producto) {
        this.productos.remove(producto);
    }

    public boolean estaPagada() {
        return "PAGADO".equalsIgnoreCase(this.estado);
    }

    public void marcarComoPagada() {
        this.estado = "PAGADO";
    }

    public void cancelar() {
        this.estado = "CANCELADO";
    }
    
    public boolean isValid() {
        if (customer == null) {
            return false;
        }
        if (productos == null || productos.isEmpty()) {
            return false;
        }
        return true;
    }
    // ===== Getters y Setters =====
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }

    public List<Product> getProductos() { return productos; }
    public void setProductos(List<Product> productos) { this.productos = productos; }

    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(LocalDateTime fechaCreacion) { this.fechaCreacion = fechaCreacion; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}
