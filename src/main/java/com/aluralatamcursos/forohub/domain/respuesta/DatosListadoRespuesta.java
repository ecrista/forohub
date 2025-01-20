package com.aluralatamcursos.forohub.domain.respuesta;

import com.aluralatamcursos.forohub.domain.topico.DatosRespuestaTopico;
import com.aluralatamcursos.forohub.domain.topico.Estado;
import com.aluralatamcursos.forohub.domain.usuario.DatosRespuestaUsuario;

import java.time.LocalDateTime;

public record DatosListadoRespuesta(
        Long id,
        String mensaje,
        LocalDateTime fechaCreacion,
        String solucion,
        Estado status,
        DatosRespuestaTopico topico,
        DatosRespuestaUsuario autor
) {
    public DatosListadoRespuesta (Respuesta respuesta){
        this(respuesta.getId(),respuesta.getMensaje(),respuesta.getFechaCreacion(), respuesta.getSolucion(), respuesta.getStatus(),new DatosRespuestaTopico(respuesta.getTopico()),new DatosRespuestaUsuario(respuesta.getAutor()));
    }
}
