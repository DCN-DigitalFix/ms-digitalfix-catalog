package cl.duoc.ms_digitalfix_catalog.dto;

public record RepuestoResponse(
        Long id,
        String codigo,
        String nombre,
        Integer stock,
        Double precioUnitario) {
}