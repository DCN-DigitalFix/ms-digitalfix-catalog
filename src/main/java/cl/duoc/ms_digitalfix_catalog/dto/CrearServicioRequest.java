package cl.duoc.ms_digitalfix_catalog.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public record CrearServicioRequest(

        @NotBlank(message = "el nombre del servicio es obligatorio")
        @Size(max = 120, message = "el nombre no puede superar 120 caracteres")
        String nombre,

        @Size(max = 500, message = "la descripcion no puede superar 500 caracteres")
        String descripcion,

        @NotNull(message = "la tarifa es obligatoria")
        @PositiveOrZero(message = "la tarifa no puede ser negativa")
        Double tarifa) {
}