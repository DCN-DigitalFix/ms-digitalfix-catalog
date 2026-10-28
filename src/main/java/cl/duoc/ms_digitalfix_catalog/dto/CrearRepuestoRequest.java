package cl.duoc.ms_digitalfix_catalog.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public record CrearRepuestoRequest(

        @NotBlank(message = "el codigo es obligatorio") @Size(max = 40) @Pattern(regexp = "^[A-Z0-9-]+$", message = "el codigo solo admite mayusculas, numeros y guion") 
        String codigo,

        @NotBlank(message = "el nombre es obligatorio") @Size(max = 120) 
        String nombre,

        @NotNull(message = "el stock es obligatorio") @PositiveOrZero(message = "el stock no puede ser negativo") 
        Integer stock,

        @NotNull @PositiveOrZero BigDecimal precioUnitario

) {

}
