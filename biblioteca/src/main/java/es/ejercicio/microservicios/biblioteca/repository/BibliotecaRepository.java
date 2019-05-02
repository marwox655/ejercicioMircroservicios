package es.ejercicio.microservicios.biblioteca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.ejercicio.microservicios.biblioteca.entity.Prestamo;



@Repository

public interface BibliotecaRepository extends JpaRepository <Prestamo, Integer>{
	
	public List<Prestamo> findByIdusuario(Integer idUsuario);
}
