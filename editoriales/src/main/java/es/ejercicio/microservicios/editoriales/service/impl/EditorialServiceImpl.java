package es.ejercicio.microservicios.editoriales.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.ejercicio.microservicios.editoriales.entity.Editorial;
import es.ejercicio.microservicios.editoriales.repository.EditorialRepository;
import es.ejercicio.microservicios.editoriales.service.EditorialService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EditorialServiceImpl implements EditorialService {

	@Autowired
	private EditorialRepository editorialRepository;


	@Override
	public List<Editorial> findAll() {

		return editorialRepository.findAll();
	}

}
