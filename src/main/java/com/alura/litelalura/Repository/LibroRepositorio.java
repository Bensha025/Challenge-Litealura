package com.alura.litelalura.Repository;

import com.alura.litelalura.Model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LibroRepositorio extends JpaRepository<Libro,Long> {
    Optional<Libro> findFirstByTituloContainsIgnoreCase(String libroBusqueda);
    @Query("SELECT l FROM Libro l WHERE l.autor IS NOT NULL")
    List<Libro> buscarAutorRegistrado();
    @Query("SELECT l FROM Libro l WHERE l.fechaDeNacimiento IS NOT NULL AND l.fechaDeNacimiento > :fechaDeNacimiento")
    List<Libro> buscarAutores(@Param("fechaDeNacimiento") String fechaDeNacimiento);
    @Query("SELECT l FROM Libro l WHERE l.idioma = :idioma")
    List<Libro> buscarLibroPorIdioma(@Param("idioma") String buscarIdioma);

}
