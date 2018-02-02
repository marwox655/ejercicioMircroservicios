package es.ejercicio.microservicios.categorias.controller;

import static org.junit.Assert.assertEquals;

import java.net.URL;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.google.gson.Gson;

import es.ejercicio.microservicios.dto.CategoriaDTO;


/**
 * @author Juan Manuel Cintas
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CategoriasControllerTestIT {

	@LocalServerPort
	private int port;

	private URL base;
	private URL baseById;
	private URL baseNuevaCategoria;

	@Autowired
	private TestRestTemplate template;

	private final String NOMBRE_SERVICIO = "categorias/getAll/";
	private final String NOMBRE_SERVICIO_BY_ID= "categorias/getCategoria/1";
	private final String NOMBRE_SERVICIO_NUEVO= "categorias/nuevaCategoria";

	private final String STATUS_OK = "200";
	private final Integer NUM_TOTAL_AUTORES = 5;

	@Before
	public void setUp() throws Exception {
	     this.base = new URL("http://localhost:" + port + "/"+ NOMBRE_SERVICIO);
	     this.baseById = new URL("http://localhost:" + port + "/"+ NOMBRE_SERVICIO_BY_ID);
	     this.baseNuevaCategoria = new URL("http://localhost:" + port + "/"+ NOMBRE_SERVICIO_NUEVO);
	}

	@Test
	public void getListCategorias() throws Exception {


		testSelectAll(5);

	 }

	@Test
	public void getCategoriaById() throws Exception {


	     ResponseEntity<CategoriaDTO> response = template.getForEntity(baseById.toString(), CategoriaDTO.class);

	     assertEquals(STATUS_OK, response.getStatusCode().toString());
	     CategoriaDTO categoria = (CategoriaDTO) response.getBody();

	     assertEquals(1, categoria.getId());

	 }

	@Test
	public void nuevaCategoria() throws Exception {
		Gson gson = new Gson();

		CategoriaDTO categoriaDTO = CategoriaDTO.builder().id(6)
						.nombre("Categoria 6")
						.build();
		String json = gson.toJson(categoriaDTO);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(json,headers);

	     ResponseEntity<CategoriaDTO> response = template.postForEntity(baseNuevaCategoria.toString(),entity,
	    		 CategoriaDTO.class);

	     assertEquals(STATUS_OK, response.getStatusCode().toString());
	     assertEquals(6,response.getBody().getId());

	     testSelectAll(6);

	 }

	private void testSelectAll(Integer totalEsperado) {
	     ResponseEntity<Object[]> response = template.getForEntity(base.toString(), Object[].class);

	     assertEquals(STATUS_OK, response.getStatusCode().toString());
	     Object[] objects = response.getBody();
	     assertEquals(totalEsperado.intValue(), objects.length);

	}
}
