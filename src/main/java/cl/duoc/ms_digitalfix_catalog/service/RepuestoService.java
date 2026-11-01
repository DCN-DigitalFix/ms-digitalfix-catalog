package cl.duoc.ms_digitalfix_catalog.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cl.duoc.ms_digitalfix_catalog.model.Repuesto;
import cl.duoc.ms_digitalfix_catalog.repository.RepuestoRepository;

@Service 
public class RepuestoService {


    private final RepuestoRepository repository;

    public RepuestoService(RepuestoRepository repository) {
        this.repository = repository;
    }


    public Repuesto buscarRepuesto(Long id) {
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Repuesto no encontrado"));
    }

    

    

}
