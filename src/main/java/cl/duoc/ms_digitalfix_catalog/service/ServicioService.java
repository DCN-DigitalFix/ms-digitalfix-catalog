package cl.duoc.ms_digitalfix_catalog.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cl.duoc.ms_digitalfix_catalog.dto.CrearServicioRequest;
import cl.duoc.ms_digitalfix_catalog.dto.ServicioResponse;
import cl.duoc.ms_digitalfix_catalog.model.Servicio;
import cl.duoc.ms_digitalfix_catalog.repository.ServicioRepository;

@Service
public class ServicioService {

    private final ServicioRepository repository;

    public ServicioService(ServicioRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<ServicioResponse> listar() {
        return repository.findAll().stream().map(this::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public List<ServicioResponse> listarActivos() {
        return repository.findByActivoTrue().stream().map(this::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public ServicioResponse obtener(Long id) {
        return toResponse(buscarServicio(id));
    }

    @Transactional
    public ServicioResponse crear(CrearServicioRequest request) {
        Servicio servicio = new Servicio();
        servicio.setNombre(request.nombre());
        servicio.setDescripcion(request.descripcion());
        servicio.setTarifa(request.tarifa());
        servicio.setActivo(true);

        return toResponse(repository.save(servicio));
    }

    /** Baja logica: el servicio deja de ofrecerse pero las ordenes que lo usaron siguen teniendo sentido. */
    @Transactional
    public ServicioResponse desactivar(Long id) {
        Servicio servicio = buscarServicio(id);
        servicio.setActivo(false);

        return toResponse(repository.save(servicio));
    }

    // -----------------------------------------------------------------------

    private Servicio buscarServicio(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Servicio no encontrado: " + id));
    }

    private ServicioResponse toResponse(Servicio e) {
        return new ServicioResponse(
                e.getId(), e.getNombre(), e.getDescripcion(), e.getTarifa(), e.isActivo());
    }
}