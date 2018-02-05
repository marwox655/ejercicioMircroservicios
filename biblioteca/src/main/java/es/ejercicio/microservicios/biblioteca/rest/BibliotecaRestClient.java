package es.ejercicio.microservicios.biblioteca.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import es.ejercicio.microservicios.dto.AutorDTO;
import es.ejercicio.microservicios.dto.CategoriaDTO;
import es.ejercicio.microservicios.dto.EditorialDTO;
import es.ejercicio.microservicios.dto.LibroBibliotecaDTO;
import es.ejercicio.microservicios.dto.LibroDTO;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class BibliotecaRestClient {


	@Value("${path.libros.getAll}")
	@Getter private String sURL_Libros;

	@Value("${path.categorias.getId}")
	@Getter private String sURL_CategoriaId;

	@Value("${path.editorial.getId}")
	@Getter private String sURL_EditorialId;

	@Value("${path.autor.getId}")
	@Getter private String sURL_AutorId;

	@Autowired
    private RestTemplate restTemplate;

	public List<LibroDTO> getListaLibros() {
		ResponseEntity<List<LibroDTO>> response =
				restTemplate.exchange(sURL_Libros,HttpMethod.GET,null,
    		 					new ParameterizedTypeReference<List<LibroDTO>>() {});
		List<LibroDTO> listaLibros = response.getBody();
		return listaLibros;

	}

	public CategoriaDTO getCategoria(Integer idCategoria) {
		String sURL = sURL_CategoriaId.concat(idCategoria.toString());
		CategoriaDTO categoria = restTemplate.getForObject(sURL, CategoriaDTO.class);

		return categoria;
	}

	public EditorialDTO getEditorial(Integer idEditorial) {
		String sURL = sURL_EditorialId.concat(idEditorial.toString());
		EditorialDTO editorial = restTemplate.getForObject(sURL, EditorialDTO.class);

		return editorial;
	}

	public AutorDTO getAutor(Integer idAutor) {
		String sURL = sURL_AutorId.concat(idAutor.toString());
		AutorDTO autor = restTemplate.getForObject(sURL, AutorDTO.class);

		return autor;
	}

    public LibroBibliotecaDTO obtenerValoresLibro(LibroDTO libro) {
    	log.debug("Se obtienen los datos del libro:" + libro);
    	CategoriaDTO categoria = getCategoria(libro.getCategoria());
    	EditorialDTO editorial = getEditorial(libro.getEditorial());
    	AutorDTO autor = getAutor(libro.getAutor());

    	return LibroBibliotecaDTO.builder()
    						.id(libro.getId())
    						.titulo(libro.getTitulo())
    						.descripcion(libro.getDescripcion())
    						.categoria(categoria.getNombre())
    						.editorial(editorial.getNombre())
    						.autor(autor.getNombre())
    						.build();

    }
}
