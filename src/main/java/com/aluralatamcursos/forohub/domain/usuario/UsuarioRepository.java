package com.aluralatamcursos.forohub.domain.usuario;

import io.micrometer.observation.ObservationFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

    @Query("Select u from Usuario u WHERE u.correoElectronico=:username")
    UserDetails findByCorreoElectronico(String username);


    Page<Usuario> findByActivoTrue(Pageable paginacion);

    Optional<Usuario> findByIdAndActivoTrue(Long id);
}
