package com.aluralatamcursos.forohub.domain.perfil;

public record DatosRespuestaPerfil(
        String nombre
) {
    public DatosRespuestaPerfil(Perfil perfil){
        this(perfil.getNombre());
    }
}
