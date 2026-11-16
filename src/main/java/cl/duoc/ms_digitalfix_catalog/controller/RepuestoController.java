package cl.duoc.ms_digitalfix_catalog.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.duoc.ms_digitalfix_catalog.dto.CrearRepuestoRequest;
import cl.duoc.ms_digitalfix_catalog.dto.DescontarStockRequest;
import cl.duoc.ms_digitalfix_catalog.dto.RepuestoResponse;
import cl.duoc.ms_digitalfix_catalog.service.RepuestoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/catalog/respuestos") 
public class RepuestoController {

    @Autowired 
    private RepuestoService repuestoService;

    @GetMapping
    public List<RepuestoResponse> listarRepuestos() {
        return repuestoService.listarRepuestos();
    }

    @GetMapping("/{id}")
    public RepuestoResponse obtenerRepuesto(@PathVariable Long id) {
        return repuestoService.obtener(id);
    }


    @PostMapping
    public ResponseEntity<RepuestoResponse> crearRepuestoResponse(@Valid @RequestBody CrearRepuestoRequest request) {
        RepuestoResponse creado = repuestoService.crearRepuestoResponse(request);
        return ResponseEntity
                .created(URI.create("/api/v1/catalog/repuestos/" + creado.id()))
                .body(creado);
    }

    @PostMapping("/{codigo}/descontar")
    public RepuestoResponse descontar(@PathVariable String codigo, @Valid @RequestBody DescontarStockRequest request) {
        return repuestoService.descontarStock(codigo, request.cantidad());
    }




}
