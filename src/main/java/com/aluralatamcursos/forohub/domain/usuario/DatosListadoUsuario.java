package com.aluralatamcursos.forohub.domain.usuario;

import com.aluralatamcursos.forohub.domain.perfil.DatosRespuestaPerfil;
import com.aluralatamcursos.forohub.domain.perfil.Perfil;
import com.aluralatamcursos.forohub.domain.topico.DatosListadoTopico;

import java.util.List;

public record DatosListadoUsuario(
        Long id,
        String nombre,
        String correoElectronico,
        List<DatosRespuestaPerfil> perfilesUsuario
) {
    public DatosListadoUsuario(Usuario usuario){
        this(usuario.getId(),usuario.getNombre(),usuario.getCorreoElectronico(),usuario.getPerfiles().stream().map(DatosRespuestaPerfil::new).toList());
    }
}
