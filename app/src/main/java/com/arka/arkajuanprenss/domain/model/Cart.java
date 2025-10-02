package com.arka.arkajuanprenss.domain.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class Cart {
     private Long id;
    private Customer customer;              // Relación con cliente
    private List<Product> productos;        // Productos en el carrito

    // ===== Constructores =====
     public Cart() {
        this.productos = new ArrayList<>();
    }

    public Cart(Long id, Customer customer, List<Product> productos) {
        this.id = id;
        this.customer = customer;
        this.productos = (productos != null) ? productos : new ArrayList<>();
    }

    // ===== Lógica de negocio =====
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

    public BigDecimal calcularTotal() {
        return productos.stream()
                .map(Product::getPrecioUnitario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public boolean estaVacio() {
        return this.productos.isEmpty();
    }

    // ===== Getters y Setters =====
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }

    public List<Product> getProductos() { return productos; }
    public void setProductos(List<Product> productos) { this.productos = productos; }
}
