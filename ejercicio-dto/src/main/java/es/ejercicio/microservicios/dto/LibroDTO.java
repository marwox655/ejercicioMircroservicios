package es.ejercicio.microservicios.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Juan Manuel Cintas
 *
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LibroDTO {

    /**
     * Id.
     */
    private int id;

    /**
     * Titulo del libro.
     */
    private String titulo;

    /**
     * Descripcion.
     */
    private String descripcion;

    /**
     * Categoria ID.
     */
    private int categoria;

    /**
     * Autor ID.
     */
    private int autor;

    /**
     * Editorial ID.
     */
    private int editorial;

    /**
     * Favorite
     */
    private Boolean favorite;

}
