package es.ejercicio.microservicios.autores.controller;

import static org.junit.Assert.assertEquals;

import java.net.URL;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import es.ejercicio.microservicios.dto.AutorDTO;


/**
 * @author Juan Manuel Cintas
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AutoresControllerTestIT {

	@LocalServerPort
	private int port;

	private URL base;
	private URL baseById;

	@Autowired
	private TestRestTemplate template;

	private final String NOMBRE_SERVICIO = "autores/getAll/";
	private final String NOMBRE_SERVICIO_BY_ID= "autores/getAutor/1";

	private final String STATUS_OK = "200";
	private final Integer NUM_TOTAL_AUTORES = 5;

	@Before
	public void setUp() throws Exception {
	     this.base = new URL("http://localhost:" + port + "/"+ NOMBRE_SERVICIO);
	     this.baseById = new URL("http://localhost:" + port + "/"+ NOMBRE_SERVICIO_BY_ID);
	}

	@Test
	public void getListAutores() throws Exception {


	     ResponseEntity<Object[]> response = template.getForEntity(base.toString(), Object[].class);

	     assertEquals(STATUS_OK, response.getStatusCode().toString());
	     Object[] objects = response.getBody();
	     assertEquals(NUM_TOTAL_AUTORES.intValue(), objects.length);

	 }

	@Test
	public void getAutorById() throws Exception {


	     ResponseEntity<AutorDTO> response = template.getForEntity(baseById.toString(), AutorDTO.class);

	     assertEquals(STATUS_OK, response.getStatusCode().toString());
	     AutorDTO autor = (AutorDTO) response.getBody();

	     assertEquals(1, autor.getId());

	 }


}
