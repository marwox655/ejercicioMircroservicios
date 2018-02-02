package es.ejercicio.microservicios.editoriales.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import es.ejercicio.microservicios.dto.EditorialDTO;
import es.ejercicio.microservicios.editoriales.entity.Editorial;
import es.ejercicio.microservicios.editoriales.service.EditorialService;

@RestController
@RequestMapping(value = "/editoriales/")
public class EditorialController {


    @Autowired
    private EditorialService editorialService;

    /** DozerMapper. */
    DozerBeanMapper mapper = new DozerBeanMapper();

    /**
     * Retorna todos las editoriales
     * @return Listado de editoriales
     * @throws SQLException
     */
    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public List<EditorialDTO> getAll() throws SQLException {

    	List<Editorial> editoriales = editorialService.findAll();
       	List<EditorialDTO> editorialesDTO = new ArrayList<EditorialDTO>();
    	if (editoriales != null)
    	{
    		for (Editorial editorial : editoriales) {
    			EditorialDTO editorialDTO= (EditorialDTO) mapper.map(editorial, EditorialDTO.class);
    			editorialesDTO.add(editorialDTO);
    		}

    	}
        return editorialesDTO;

    }

}
