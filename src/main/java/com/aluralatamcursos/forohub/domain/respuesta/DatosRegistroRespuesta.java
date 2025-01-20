package com.aluralatamcursos.forohub.domain.respuesta;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record DatosRegistroRespuesta(
         @NotBlank
         String mensaje,
         @NotBlank
         String solucion,
         @NotNull
         Long idTopico,
         @NotNull
         Long idUsuario
) {
}
