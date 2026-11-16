package cl.duoc.ms_digitalfix_catalog.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record DescontarStockRequest(

        @NotNull(message = "la cantidad es obligatoria")
        @Positive(message = "la cantidad debe ser mayor que cero")
        Integer cantidad) {
}