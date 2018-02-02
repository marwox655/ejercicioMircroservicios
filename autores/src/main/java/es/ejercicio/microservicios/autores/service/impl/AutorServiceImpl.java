package es.ejercicio.microservicios.autores.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.ejercicio.microservicios.autores.entity.Autor;
import es.ejercicio.microservicios.autores.repository.AutorRepository;
import es.ejercicio.microservicios.autores.service.AutorService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AutorServiceImpl implements AutorService {

	@Autowired
	private AutorRepository autorRepository;


	@Override
	public List<Autor> findAll() {

		return autorRepository.findAll();
	}

}
