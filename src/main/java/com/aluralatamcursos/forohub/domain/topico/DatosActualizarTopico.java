package com.aluralatamcursos.forohub.domain.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosActualizarTopico(

        String titulo,
        String mensaje,
        String nombreCurso
) {
}
