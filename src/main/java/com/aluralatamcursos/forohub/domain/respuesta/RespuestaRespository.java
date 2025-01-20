package com.aluralatamcursos.forohub.domain.respuesta;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RespuestaRespository extends JpaRepository<Respuesta,Long> {

    @Query("Select r from Respuesta r where r.status != ELIMINADO")
    Page<Respuesta> findAllByStatusNotELIMINADO(Pageable paginacion);
    @Query("Select r from Respuesta r where r.id=:id AND r.status != ELIMINADO")
    Optional<Respuesta> findByIdAndStatusNotELIMINADO(Long id);
}
