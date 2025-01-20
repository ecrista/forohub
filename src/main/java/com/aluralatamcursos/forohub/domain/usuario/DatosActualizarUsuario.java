package com.aluralatamcursos.forohub.domain.usuario;

import java.util.List;

public record DatosActualizarUsuario(
        String nombre,
        String email,
        String contrasena,
        List<String> perfiles
) {
}
