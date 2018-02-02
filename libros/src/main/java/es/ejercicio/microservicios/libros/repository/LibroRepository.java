package es.ejercicio.microservicios.libros.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.ejercicio.microservicios.libros.entity.Libro;



@Repository
public interface LibroRepository extends JpaRepository<Libro, Integer> {

    public List<Libro> findByFavoriteTrue();
}