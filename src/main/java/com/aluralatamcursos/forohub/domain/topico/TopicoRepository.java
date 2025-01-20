package com.aluralatamcursos.forohub.domain.topico;

import io.micrometer.observation.ObservationFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TopicoRepository extends JpaRepository<Topico,Long> {
    Page<Topico> findAll(Pageable paginacion);

    @Query("Select t from Topico t where status != ELIMINADO")
    Page<Topico> findAllByStatusNotELIMINADO(Pageable paginacion);

    @Query("Select r from Topico r where r.id=:id AND r.status != ELIMINADO")
    Optional<Topico> findByIdAndStatusNotELIMINADO(Long id);
}
