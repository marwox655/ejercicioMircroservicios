package es.ejercicio.microservicios.libros.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.ejercicio.microservicios.libros.entity.Libro;
import es.ejercicio.microservicios.libros.repository.LibroRepository;
import es.ejercicio.microservicios.libros.service.LibroService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LibroServiceImpl implements LibroService {

	@Autowired
	private LibroRepository libroRepository;


	@Override
	public List<Libro> findByFavoriteTrue() {

		return libroRepository.findByFavoriteTrue();
	}


	@Override
	public List<Libro> findAll() {

		return libroRepository.findAll();
	}

}
