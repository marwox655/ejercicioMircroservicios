package es.ejercicio.microservicios.dto;

import lombok.AllArgsConstructor;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class BibliotecaDTO {
	
	 /**
     * Id.
     */
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
	
	private int idusuario;
}
