package com.aluralatamcursos.forohub.domain.respuesta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosActualizarRespuesta(
        String mensaje,
        String solucion,
        Long idTopico
) {
}
