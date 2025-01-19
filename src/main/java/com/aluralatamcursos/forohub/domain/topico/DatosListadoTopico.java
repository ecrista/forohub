package com.aluralatamcursos.forohub.domain.topico;

import com.aluralatamcursos.forohub.domain.curso.Curso;
import com.aluralatamcursos.forohub.domain.usuario.DatosRespuestaUsuario;
import com.aluralatamcursos.forohub.domain.usuario.Usuario;

import java.time.LocalDateTime;

public record DatosListadoTopico(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        Estado estado,
        DatosRespuestaUsuario autor,
        String curso

) {
    public DatosListadoTopico(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion(),topico.getStatus(),new DatosRespuestaUsuario(topico.getAutor()),topico.getCurso().getNombre());
    }
}
