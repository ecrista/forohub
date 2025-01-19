package com.aluralatamcursos.forohub.domain.topico;

import com.aluralatamcursos.forohub.domain.curso.Curso;
import com.aluralatamcursos.forohub.domain.respuesta.Respuesta;
import com.aluralatamcursos.forohub.domain.usuario.DatosRespuestaUsuario;
import com.aluralatamcursos.forohub.domain.usuario.Usuario;

import java.time.LocalDateTime;
import java.util.List;

public record DatosRespuestaTopico(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        Estado status,
        DatosRespuestaUsuario autor,
        String curso
) {
    public DatosRespuestaTopico(Topico topico){
        this(topico.getId(), topico.getTitulo(),topico.getMensaje(),topico.getFechaCreacion(),topico.getStatus(),new DatosRespuestaUsuario(topico.getAutor()),topico.getCurso().getNombre());
    }
}
