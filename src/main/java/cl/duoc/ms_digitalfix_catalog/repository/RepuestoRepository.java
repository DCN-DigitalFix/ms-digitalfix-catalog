package cl.duoc.ms_digitalfix_catalog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.duoc.ms_digitalfix_catalog.model.Repuesto;

public interface RepuestoRepository extends JpaRepository<Repuesto, Long> {

    boolean existsByCodigo(String codigo);

    Optional<Repuesto> findByCodigo(String codigo);

}
