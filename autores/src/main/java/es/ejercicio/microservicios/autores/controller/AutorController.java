package es.ejercicio.microservicios.autores.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import es.ejercicio.microservicios.autores.entity.Autor;
import es.ejercicio.microservicios.autores.service.AutorService;
import es.ejercicio.microservicios.dto.AutorDTO;



@RestController
@RequestMapping(value = "/autores/")
public class AutorController {


    @Autowired
    private AutorService autorService;

    /** DozerMapper. */
    DozerBeanMapper mapper = new DozerBeanMapper();

    /**
     * Retorna todos los autores
     * @return Listado de autores
     * @throws SQLException
     */
    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public List<AutorDTO> getAll() throws SQLException {

    	List<Autor> autores = autorService.findAll();
       	List<AutorDTO> autoresDTO = new ArrayList<AutorDTO>();
    	if (autores != null)
    	{
    		for (Autor autor : autores) {
    			AutorDTO autorDTO= (AutorDTO) mapper.map(autor, AutorDTO.class);
    			autoresDTO.add(autorDTO);
    		}

    	}
        return autoresDTO;

    }

}
