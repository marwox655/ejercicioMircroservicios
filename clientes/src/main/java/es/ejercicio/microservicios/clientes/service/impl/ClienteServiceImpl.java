package es.ejercicio.microservicios.clientes.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.ejercicio.microservicios.clientes.repository.ClienteRepository;
import es.ejercicio.microservicios.clientes.entity.Cliente;
import es.ejercicio.microservicios.clientes.service.ClienteService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service

public class ClienteServiceImpl implements ClienteService{
		
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Override
	public List<Cliente> findAll() {
		log.debug("Se obtienen todos los autores.");

		return clienteRepository.findAll();
	}
	
	@Override
	public Cliente findById(Integer id) {
		log.debug("Se obtiene el autor con id:" + id);
		return clienteRepository.findOne(id);
	}
	
	@Override
	public void deleteById(Integer id) {
		log.debug("Se elimina el cliente con id:" + id);
		 clienteRepository.delete(id);

	}


	@Override
	public Cliente nuevoCliente(Cliente cliente) {
		log.debug("Se añade el autor:" + cliente);
		return clienteRepository.save(cliente);
	}

}
