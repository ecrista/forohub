package com.aluralatamcursos.forohub.domain.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

    @Query("Select u from Usuario u WHERE u.correoElectronico=:username")
    UserDetails findByCorreoElectronico(String username);
}
