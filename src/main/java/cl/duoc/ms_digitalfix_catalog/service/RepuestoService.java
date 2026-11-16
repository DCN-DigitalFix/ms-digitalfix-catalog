package cl.duoc.ms_digitalfix_catalog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cl.duoc.ms_digitalfix_catalog.dto.CrearRepuestoRequest;
import cl.duoc.ms_digitalfix_catalog.dto.RepuestoResponse;
import cl.duoc.ms_digitalfix_catalog.exception.CodigoDuplicadoException;
import cl.duoc.ms_digitalfix_catalog.exception.NotFoundException;
import cl.duoc.ms_digitalfix_catalog.exception.StockInsuficienteException;
import cl.duoc.ms_digitalfix_catalog.model.Repuesto;
import cl.duoc.ms_digitalfix_catalog.repository.RepuestoRepository;

@Service 
public class RepuestoService {


    @Autowired 
    private RepuestoRepository repository;

    @Transactional
    public RepuestoResponse crearRepuestoResponse(CrearRepuestoRequest crearRepuestoRequest) {
        
        if (repository.existsByCodigo(crearRepuestoRequest.codigo())) {
        throw new CodigoDuplicadoException("Ya existe un repuesto con el codigo " + crearRepuestoRequest.codigo());
        }

        Repuesto repuesto = new Repuesto();
        repuesto.setNombre(crearRepuestoRequest.nombre());
        repuesto.setCodigo(crearRepuestoRequest.codigo());
        repuesto.setStock(crearRepuestoRequest.stock());
        repuesto.setPrecioUnitario(crearRepuestoRequest.precioUnitario());

        return toResponse(repository.save(repuesto));
    }

    // ------------------------------Descontar--------------------------------------

    @Transactional
    public RepuestoResponse descontarStock(String codigo, int cantidad) {

    if (cantidad <= 0) {
        throw new IllegalArgumentException("La cantidad a descontar debe ser mayor que cero");
    }

    Repuesto repuesto = repository.findByCodigo(codigo)
            .orElseThrow(() -> new NotFoundException("No existe el repuesto con codigo " + codigo));

    if (repuesto.getStock() < cantidad) {
        throw new StockInsuficienteException(
                "Stock insuficiente de " + codigo + ": hay " + repuesto.getStock() + ", se requieren " + cantidad);
    }

    repuesto.setStock(repuesto.getStock() - cantidad);
    return toResponse(repository.save(repuesto));
}

    // -----------------------------------------------------------------------

    // -----------------------Listados------------------------------

    @Transactional(readOnly = true)
    public RepuestoResponse obtener(Long id) {
        return toResponse(buscarRepuesto(id));
    }

    @Transactional(readOnly = true)
    public List<RepuestoResponse> listarRepuestos() {
        return repository.findAll().stream().map(this::toResponse).toList();
    }

    // -----------------------------------------------------------------------

    // -----------------------------Buscar---------------------------------


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


    // -----------------------------------------------------------------------

    

    

    

}
