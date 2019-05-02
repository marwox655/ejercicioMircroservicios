package es.ejercicio.microservicios.biblioteca.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sun.tools.sjavac.Log;

import es.ejercicio.microservicios.biblioteca.entity.Prestamo;
import es.ejercicio.microservicios.biblioteca.repository.BibliotecaRepository;
import es.ejercicio.microservicios.biblioteca.service.BibliotecaService;
import es.ejercicio.microservicios.biblioteca.service.impl.BibliotecaServiceImpl;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service

public class BibliotecaServiceImpl implements BibliotecaService{
	
	@Autowired
	BibliotecaRepository prestamoRepository;
	
	@Override
	public List<Prestamo> findAll() {
		
		log.debug("Devuelve todos los prestamos");
		
		return prestamoRepository.findAll();
	}

	@Override
	public Prestamo findById(Integer id) {
		log.debug("Se obtiene el prestamo con id:" + id);
		
		return prestamoRepository.findOne(id);
	}

	@Override
	public void deleteById(Integer id) {

		log.debug("Se elimina el prestamo con el id "+ id);
		
		prestamoRepository.delete(id);
	}

	@Override
	public Prestamo nuevoPrestamo(Prestamo prestamos) {
		
		log.debug("Se añade el prestamo " + prestamos);
		
		return prestamoRepository.save(prestamos);
	}

	@Override
	public List<Prestamo> obtenerLibrosUsuario(Integer idUsuario) {

		log.debug("Se obtiene la lista de prestamos con id de usuario");
		
		List<Prestamo> listPrestamo = prestamoRepository.findByIdusuario(idUsuario);
		
		if (listPrestamo != null) {
			log.debug("---------------> Tamaño lista: " + listPrestamo.size());
			log.debug("--------------- > Usuario: " + listPrestamo.get(0).getIdusuario());
			log.debug("--------------- > Libro: " + listPrestamo.get(0).getIdlibro());
			log.debug("--------------- > Fecha: " + listPrestamo.get(0).getFecha());
		}
		
		return listPrestamo;
	}

}
