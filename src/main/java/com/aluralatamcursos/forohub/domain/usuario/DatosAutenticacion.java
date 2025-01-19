package com.aluralatamcursos.forohub.domain.usuario;

import jakarta.validation.constraints.NotBlank;

public record DatosAutenticacion(@NotBlank String email, @NotBlank String contrasena) {
}