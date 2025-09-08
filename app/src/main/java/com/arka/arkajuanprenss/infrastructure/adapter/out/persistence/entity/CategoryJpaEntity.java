package com.arka.arkajuanprenss.infrastructure.adapter.out.persistence.entity;
import jakarta.persistence.*;

@Entity
@Table(name = "categorias")
public class CategoryJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "categoria_id")
    private Long id;

    @Column(name = "nombre", nullable = false, length = 255)
    private String nombre;

    // Constructores
    public CategoryJpaEntity() {}

    public CategoryJpaEntity(String nombre) {
        this.nombre = nombre;
    }

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
}
