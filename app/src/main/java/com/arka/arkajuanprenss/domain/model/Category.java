package com.arka.arkajuanprenss.domain.model;

public class Category {
    private Long id;
    private String nombre;

    // Constructores
    public Category() {}

    public Category(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    // Lógica de negocio
    public boolean isValidName() {
        return nombre != null && !nombre.trim().isEmpty();
    }

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    @Override
    public String toString() {
        return "Category{id=" + id + ", nombre='" + nombre + "'}";
    }
}