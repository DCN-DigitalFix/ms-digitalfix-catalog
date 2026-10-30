package cl.duoc.ms_digitalfix_catalog.dto;

public record ServicioResponse(
        Long id,
        String nombre,
        String descripcion,
        Double tarifa,
        boolean activo) {
}