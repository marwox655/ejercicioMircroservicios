package es.ejercicio.microservicios.biblioteca.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import es.ejercicio.microservicios.biblioteca.rest.BibliotecaRestClient;
import es.ejercicio.microservicios.dto.LibroBibliotecaDTO;
import es.ejercicio.microservicios.dto.LibroDTO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/biblioteca/")
public class BibliotecaController {


	@Autowired
    private BibliotecaRestClient biblitecaTemplate;

    /** DozerMapper. */
    DozerBeanMapper mapper = new DozerBeanMapper();

    /**
     * Retorna todos los libros
     * @return Listado de libros
     * @throws SQLException
     */
    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public List<LibroBibliotecaDTO> getAll() throws SQLException {


		List<LibroDTO> listaBiblioteca = biblitecaTemplate.getListaLibros();
		List<LibroBibliotecaDTO> librosBiblioteca = new ArrayList<LibroBibliotecaDTO>();

    	log.debug("Total de Libros Obtenidos:" + listaBiblioteca.size());
    	for(LibroDTO libro : listaBiblioteca) {
    		log.debug("Libro:" + libro);
    		LibroBibliotecaDTO bibliotecaLibro = biblitecaTemplate.obtenerValoresLibro(libro);
    		librosBiblioteca.add(bibliotecaLibro);
    	}


    	return librosBiblioteca;
    }

}
