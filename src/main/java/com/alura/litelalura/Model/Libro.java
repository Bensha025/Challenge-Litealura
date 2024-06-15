package com.alura.litelalura.Model;

import jakarta.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String titulo;

    private String autor;
    private String fechaDeNacimiento;
    private Double evaluacion;
    private String idioma;

    // Constructor sin argumentos requerido por JPA
    public Libro() {
        // Constructor vacío requerido por JPA
    }

    public Libro(Long id, String titulo, String autor, String fechaDeNacimiento, Double evaluacion, String idioma) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.fechaDeNacimiento = fechaDeNacimiento;
        this.evaluacion = evaluacion;
        this.idioma = idioma;
    }

    public Libro(DatosLibro datosLibro){
        this.titulo = datosLibro.titulo();
        this.autor = datosLibro.autor().stream()
                .map(DatosAutor::toString)
                .collect(Collectors.joining(", "));
        this.fechaDeNacimiento = datosLibro.autor().stream()
                .map(DatosAutor::fechaDeNacimiento)
                .collect(Collectors.joining(", "));
        this.evaluacion = datosLibro.descargas();
        this.idioma = datosLibro.idioma().stream()
                .collect(Collectors.joining(", "));
    }

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(String fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public Double getEvaluacion() {
        return evaluacion;
    }

    public void setEvaluacion(Double evaluacion) {
        this.evaluacion = evaluacion;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }
}
