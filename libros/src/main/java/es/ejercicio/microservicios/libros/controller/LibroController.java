package es.ejercicio.microservicios.libros.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import es.ejercicio.microservicios.libros.entity.Libro;
import es.ejercicio.microservicios.libros.service.impl.LibroServiceImpl;



@RestController
@RequestMapping(value = "/libros/")
class LibroController {


    @Autowired
    private LibroServiceImpl libroService;


    /**
     * Retorna todos los libros
     * @return Listado de libros
     * @throws SQLException
     */
    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public List<Libro> getAll() throws SQLException {

        return libroService.findAll();

    }


    /**
     * Retorna todos los libros favoritos
     * @return Lista de libros favoritos
     * @throws SQLException
     */
    @RequestMapping(value = "/getFavoritos", method = RequestMethod.GET)
    public List<Libro> getFavoritos() throws SQLException {

        return libroService.findByFavoriteTrue();
    }


}
