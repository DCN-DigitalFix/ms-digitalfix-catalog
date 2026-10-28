package cl.duoc.ms_digitalfix_catalog.dto;

public record CrearServicioRequest(


    String nombre,
    String descripcion,
    Double precio,
    Boolean activo

) {

}
