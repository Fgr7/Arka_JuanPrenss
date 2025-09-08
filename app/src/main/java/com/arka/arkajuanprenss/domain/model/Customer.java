package com.arka.arkajuanprenss.domain.model;

public class Customer {
    private Long id;
    private String nombre;
    private String email;
    private String telefono;
    private String ciudad;
    private String pais;

    // Constructores
    public Customer() {}

    // Lógica de negocio
    public boolean isValidEmail() {
        return email != null && email.contains("@") && email.contains(".");
    }

    public boolean hasCompleteProfile() {
        return nombre != null && email != null && telefono != null;
    }

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    
    public String getCiudad() { return ciudad; }
    public void setCiudad(String ciudad) { this.ciudad = ciudad; }
    
    public String getPais() { return pais; }
    public void setPais(String pais) { this.pais = pais; }
}
