package es.ejercicio.microservicios.biblioteca.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import es.ejercicio.microservicios.biblioteca.entity.Prestamo;
import es.ejercicio.microservicios.biblioteca.entity.Prestamo.PrestamoBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Prestamo {
	
	 /**
     * Id.
     */
    @Id
	private int id;
    
    /**
     * fecha.
     */
	private String fecha;
	
	 /**
     * fechaDev.
     */
	private String fechadev;
	
	/**
	 * IdLibro
	 */
	private int idlibro;
	
	
	/**
	 * IdUsuario
	 * */
	
	private int idusuario;
	 
}
