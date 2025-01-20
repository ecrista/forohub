package com.aluralatamcursos.forohub.domain.usuario;

import com.aluralatamcursos.forohub.domain.perfil.DatosRespuestaPerfil;

import java.util.List;

public record DatosRespuestaUsuarioPrivado(
        Long id,
        String nombre,
        String correoElectronico,
        List<DatosRespuestaPerfil> perfilesUsuario
) {
    public DatosRespuestaUsuarioPrivado(Usuario usuario){
        this(usuario.getId(),usuario.getNombre(),usuario.getCorreoElectronico(),usuario.getPerfiles().stream().map(DatosRespuestaPerfil::new).toList());
    }
}
