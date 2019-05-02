package es.ejercicio.microservicios.biblioteca.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import es.ejercicio.microservicios.biblioteca.entity.Prestamo;
import es.ejercicio.microservicios.biblioteca.rest.BibliotecaRestClient;
import es.ejercicio.microservicios.biblioteca.service.BibliotecaService;
import es.ejercicio.microservicios.dto.AutorDTO;
import es.ejercicio.microservicios.dto.BibliotecaDTO;
import es.ejercicio.microservicios.dto.LibroBibliotecaDTO;
import es.ejercicio.microservicios.dto.LibroDTO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/biblioteca/")
public class BibliotecaController {


	@Autowired
    private BibliotecaRestClient biblitecaTemplate;
	
	@Autowired
	private BibliotecaService bibliotecaService;

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
		return getListaBiblioteca(listaBiblioteca);
    }

    /**
     * Retorna todos los libros favoritos
     * @return Listado de libros favoritos
     * @throws SQLException
     */
    @RequestMapping(value = "/getAllFavoritos", method = RequestMethod.GET)
    public List<LibroBibliotecaDTO> getAllFavoritos() throws SQLException {


		List<LibroDTO> listaBiblioteca = biblitecaTemplate.getListaLibrosFavoritos();

    	return getListaBiblioteca(listaBiblioteca);
    }

    /**
     * Elimina el Autor
     * @throws SQLException
     */
    @RequestMapping(value = "/deleteAutor/{id}", method = RequestMethod.DELETE)
    public HttpStatus deleteAutor(@PathVariable("id") String id) throws SQLException {


    	Integer idAutor = 0;
    	try
    	{
    		idAutor = Integer.parseInt(id);
    	} catch (NumberFormatException ex) {
    		log.error("Se ha producido un error, el id no es un valor numerico:" + ex.getMessage());
    		return HttpStatus.NOT_FOUND;
    	}
    	LibroDTO libroAutor = LibroDTO.builder().autor(idAutor).build();
    	List<LibroDTO> listaBiblioteca = biblitecaTemplate.getListaLibrosByExample(libroAutor);
    	if (listaBiblioteca.isEmpty()) {
    		log.debug("Se elimina el autor.");
    		biblitecaTemplate.deleteAutor(idAutor);
    		return HttpStatus.OK;
    	} else {
    		log.debug("No se puede eliminar el autor ya que tiene libros en la biblioteca.");
    		return HttpStatus.NOT_ACCEPTABLE;
    	}

    }


    private List<LibroBibliotecaDTO> getListaBiblioteca(List<LibroDTO> listaBiblioteca) {

    	List<LibroBibliotecaDTO> librosBiblioteca = new ArrayList<LibroBibliotecaDTO>();
    	log.debug("Total de Libros Obtenidos:" + listaBiblioteca.size());
    	for(LibroDTO libro : listaBiblioteca) {
    		log.debug("Libro:" + libro);
    		LibroBibliotecaDTO bibliotecaLibro = biblitecaTemplate.obtenerValoresLibro(libro);
    		librosBiblioteca.add(bibliotecaLibro);
    	}
    	return librosBiblioteca;
    }
    
    
    @RequestMapping(value ="/getLibro/{id}", method = RequestMethod.GET)
    private LibroDTO getLibro(@PathVariable("id") String id)  throws SQLException {
    	Integer idLibro = 0;
    	try
    	{
    		idLibro = Integer.parseInt(id);
    	} catch (NumberFormatException ex) {
    		log.error("Se ha producido un error, el id no es un valor numerico:" + ex.getMessage());
    	
    	}
    	
    	LibroDTO libro = biblitecaTemplate.getLibro(idLibro);
    	
    	return libro;
    }
    
    /**
     * Retorna el Prestamo del id
     * @return Prestamo
     * @throws SQLException
     */
    @RequestMapping(value = "/getPrestamoById/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<BibliotecaDTO>> getPrestamoById(@PathVariable("id") String id) throws SQLException {
    	Integer idPrestamo = null;
    	BibliotecaDTO bibliotecaDTO = null;
    	List<BibliotecaDTO> listPrestamos = new ArrayList();
    	try
    	{
    		idPrestamo = Integer.parseInt(id);
    	} catch (NumberFormatException ex) {
    		log.error("Se ha producido un error, el id no es un valor numerico:" + ex.getMessage());
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(listPrestamos);
    	}
    	
    	//Obtengo la lista de libros de un usuario
    	List<Prestamo> prestamo = bibliotecaService.obtenerLibrosUsuario(idPrestamo);
    	
    	
    	// Mapear la lista la base de datos a la lista de salida (DTO)
    	for(Prestamo biblio:prestamo) {
    		
    		bibliotecaDTO= (BibliotecaDTO) mapper.map(biblio, BibliotecaDTO.class);
    		listPrestamos.add(bibliotecaDTO);
    	}
    	

       	return ResponseEntity.status(HttpStatus.OK).body(listPrestamos);

    }
    														
}
