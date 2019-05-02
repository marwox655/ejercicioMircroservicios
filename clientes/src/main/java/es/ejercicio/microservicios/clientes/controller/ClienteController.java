	package es.ejercicio.microservicios.clientes.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import es.ejercicio.microservicios.clientes.entity.Cliente;
import es.ejercicio.microservicios.clientes.service.ClienteService;
import es.ejercicio.microservicios.dto.ClienteDTO;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
@RequestMapping(value = "/clientes/")

public class ClienteController {
	
	 @Autowired
	    private ClienteService clienteService;

	    /** DozerMapper. */
	    DozerBeanMapper mapper = new DozerBeanMapper();

	    /**
	     * Retorna todos los clientes
	     * @return Listado de clientes
	     * @throws SQLException
	     */
	    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
	    public List<ClienteDTO> getAll() throws SQLException {

	    	List<Cliente> clientes = clienteService.findAll();
	       	List<ClienteDTO> clientesDTO = new ArrayList<ClienteDTO>();
	    	if (clientes != null)
	    	{
	    		for (Cliente cliente : clientes) {
	    			ClienteDTO clienteDTO= (ClienteDTO) mapper.map(cliente, ClienteDTO.class);
	    			clientesDTO.add(clienteDTO);
	    		}

	    	}
	        return clientesDTO;

	    }

	    /**
	     * Retorna el cliente del id
	     * @return Cliente
	     * @throws SQLException
	     */
	    @RequestMapping(value = "/getCliente/{id}", method = RequestMethod.GET)
	    public ResponseEntity<ClienteDTO> getCliente(@PathVariable("id") String id) throws SQLException {
	    	Integer idCliente = 0;
	    	try
	    	{
	    		idCliente = Integer.parseInt(id);
	    	} catch (NumberFormatException ex) {
	    		log.error("Se ha producido un error, el id no es un valor numerico:" + ex.getMessage());
	    		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ClienteDTO());
	    	}
	    	Cliente cliente = clienteService.findById(idCliente);
	    	ClienteDTO clienteDTO= (ClienteDTO) mapper.map(cliente, ClienteDTO.class);

	       	return ResponseEntity.status(HttpStatus.OK).body(clienteDTO);

	    }

	    /**
	     * Elimina el cliente del id
	     * @throws SQLException
	     */
	    @RequestMapping(value = "/deleteCliente/{id}", method = RequestMethod.DELETE)
	    public HttpStatus deleteCliente(@PathVariable("id") String id) throws SQLException {
	    	Integer idCliente = 0;
	    	try
	    	{
	    		idCliente = Integer.parseInt(id);
	    	} catch (NumberFormatException ex) {
	    		log.error("Se ha producido un error, el id no es un valor numerico:" + ex.getMessage());
	    		return HttpStatus.NOT_FOUND;
	    	}
	    	clienteService.deleteById(idCliente);

	       	return HttpStatus.OK;
	    }


	    /**
	     * Añade un nuevo cliente
	     * @return Cliente
	     * @throws SQLException
	     */
	    @RequestMapping(value = "/nuevoCliente", method = RequestMethod.POST)
	    public ResponseEntity<ClienteDTO> nuevoCliente(@RequestBody ClienteDTO input) throws SQLException {
	    	log.debug("Se intenta insertar el cliente:" + input);

	    	Cliente cliente = Cliente.builder().id(input.getId())
	    											 .nombre(input.getNombre())
	    											 .build();

	    	Cliente nuevoCliente = clienteService.nuevoCliente(cliente);
	    	ClienteDTO clienteDTO= (ClienteDTO) mapper.map(nuevoCliente, ClienteDTO.class);

	       	return ResponseEntity.status(HttpStatus.OK).body(clienteDTO);

	    }

}
