package es.ejercicio.microservicios.autores.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import es.ejercicio.microservicios.autores.entity.Autor;
import es.ejercicio.microservicios.autores.service.impl.AutorServiceImpl;



@RestController
@RequestMapping(value = "/autores/")
public class AutorController {


    @Autowired
    private AutorServiceImpl autorService;


    /**
     * Retorna todos los autores
     * @return Listado de autores
     * @throws SQLException
     */
    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public List<Autor> getAll() throws SQLException {

        return autorService.findAll();

    }

}
