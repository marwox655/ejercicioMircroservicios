package es.ejercicio.microservicios.biblioteca.service;

import java.util.List;

import es.ejercicio.microservicios.biblioteca.entity.Prestamo;

public interface BibliotecaService {
	
	public List<Prestamo> findAll();

    public Prestamo findById(Integer id);

    public void deleteById(Integer id);

    public Prestamo nuevoPrestamo(Prestamo prestamos);
    
    public List<Prestamo> obtenerLibrosUsuario(Integer idUsuario);

}
