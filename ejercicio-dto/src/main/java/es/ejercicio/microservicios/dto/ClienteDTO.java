package es.ejercicio.microservicios.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Fernando Piote
 *
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDTO {

    /**
     * Id.
     */
    private int id;

    /**
     * Nombre del cliente.
     */
    private String nombre;


}
