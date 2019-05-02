package es.ejercicio.microservicios.clientes.service;

import java.util.List;

import es.ejercicio.microservicios.clientes.entity.Cliente;

public interface ClienteService {

    public List<Cliente> findAll();

    public Cliente findById(Integer id);

    public void deleteById(Integer id);

    public Cliente nuevoCliente(Cliente cliente);
}