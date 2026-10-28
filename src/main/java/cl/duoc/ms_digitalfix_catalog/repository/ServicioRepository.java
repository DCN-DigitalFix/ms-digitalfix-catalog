package cl.duoc.ms_digitalfix_catalog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.duoc.ms_digitalfix_catalog.model.Servicio;

public interface ServicioRepository extends JpaRepository<Servicio, Long> {

    List<Servicio> findByActivoTrue();

}
