package cl.duoc.ms_digitalfix_catalog.exception;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiError> handleNoEncontrado(NotFoundException ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiError.of(
                404, "Not Found", ex.getMessage(), path(request)));
    }

    @ExceptionHandler(CodigoDuplicadoException.class)
    public ResponseEntity<ApiError> handleDuplicado(CodigoDuplicadoException ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ApiError.of(
                409, "Conflict", ex.getMessage(), path(request)));
    }

    @ExceptionHandler(StockInsuficienteException.class)
    public ResponseEntity<ApiError> handleStockInsuficiente(
        StockInsuficienteException ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ApiError.of(
            409, "Conflict", ex.getMessage(), path(request)));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
public ResponseEntity<ApiError> handleValidacion(
        MethodArgumentNotValidException ex, WebRequest request) {

    List<String> detalles = ex.getBindingResult().getFieldErrors().stream()
            .map(error -> error.getField() + ": " + error.getDefaultMessage())
            .toList();

    return ResponseEntity.badRequest().body(ApiError.conDetalles(
            400, "Bad Request", "La peticion tiene campos invalidos",
            path(request), detalles));
    }

    // -----------------------------------------------------------------------

    /** WebRequest entrega la ruta como "uri=/api/v1/catalog/..."; aqui se limpia. */
    private String path(WebRequest request) {
        return request.getDescription(false).replace("uri=", "");
    }
}