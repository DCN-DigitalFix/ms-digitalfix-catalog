package cl.duoc.ms_digitalfix_catalog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cl.duoc.ms_digitalfix_catalog.dto.CrearRepuestoRequest;
import cl.duoc.ms_digitalfix_catalog.dto.RepuestoResponse;
import cl.duoc.ms_digitalfix_catalog.exception.CodigoDuplicadoException;
import cl.duoc.ms_digitalfix_catalog.exception.NotFoundException;
import cl.duoc.ms_digitalfix_catalog.model.Repuesto;
import cl.duoc.ms_digitalfix_catalog.repository.RepuestoRepository;

@Service 
public class RepuestoService {


    @Autowired 
    private RepuestoRepository repository;

    @Transactional
    public RepuestoResponse crear(CrearRepuestoRequest request) {
        
        if (repository.existsByCodigo(request.codigo())) {
        throw new CodigoDuplicadoException("Ya existe un repuesto con el codigo " + request.codigo());
        }

        Repuesto repuesto = new Repuesto();
        repuesto.setNombre(request.nombre());
        repuesto.setCodigo(request.codigo());
        repuesto.setStock(request.stock());
        repuesto.setPrecioUnitario(request.precioUnitario());

        return toResponse(repository.save(repuesto));
    }

    @Transactional(readOnly = true)
    public RepuestoResponse obtener(Long id) {
        return toResponse(buscarRepuesto(id));
    }

    @Transactional(readOnly = true)
    public List<RepuestoResponse> listarRepuestos() {
        return repository.findAll().stream().map(this::toResponse).toList();
    }


    private Repuesto buscarRepuesto(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("No existe el repuesto " + id));
    }

    private RepuestoResponse toResponse(Repuesto repuesto) {
        return new RepuestoResponse(
                repuesto.getId(),
                repuesto.getCodigo(),
                repuesto.getNombre(),
                repuesto.getStock(),
                repuesto.getPrecioUnitario()
        );
    }

    

    

    

}
