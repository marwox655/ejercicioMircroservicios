package es.ejercicio.microservicios.clientes.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Juan Manuel Cintas
 *
 */
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor


public class Cliente {

	 /**
     * Id.
     */
    @Id
    private int id;

    /**
     * Nombre del cliente.
     */
    private String nombre;

	
}
