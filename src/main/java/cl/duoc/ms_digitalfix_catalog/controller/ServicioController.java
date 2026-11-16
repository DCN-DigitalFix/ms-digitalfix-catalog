package cl.duoc.ms_digitalfix_catalog.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cl.duoc.ms_digitalfix_catalog.dto.CrearServicioRequest;
import cl.duoc.ms_digitalfix_catalog.dto.ServicioResponse;
import cl.duoc.ms_digitalfix_catalog.service.ServicioService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/catalog/services") 
public class ServicioController {

    @Autowired 
    private ServicioService servicioService;

    

    @GetMapping
    public List<ServicioResponse> listar(@RequestParam(defaultValue = "false") boolean soloActivos) {
        return soloActivos ? servicioService.listarActivos() : servicioService.listar();
    }

    @GetMapping("/{id}")
    public ServicioResponse obtener(@PathVariable Long id) {
        return servicioService.obtener(id);
    }

    @PostMapping
    public ResponseEntity<ServicioResponse> crear(@Valid @RequestBody CrearServicioRequest request) {
        ServicioResponse creado = servicioService.crear(request);
        return ResponseEntity
                .created(URI.create("/api/v1/catalog/services/" + creado.id()))
                .body(creado);
    }

    @DeleteMapping("/eliminar/{id}")
    public ServicioResponse desactivar(@PathVariable Long id) {
        return servicioService.desactivar(id);
    }
    
    
}
