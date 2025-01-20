package com.aluralatamcursos.forohub.domain;

import org.springframework.dao.DataIntegrityViolationException;

public class ValidacionException extends RuntimeException {

    public ValidacionException(String mensaje){
        super(mensaje);
    }

    public ValidacionException(DataIntegrityViolationException e){
        // Analizar el mensaje de la excepción para obtener detalles
        String mensajeOriginal = e.getRootCause().getMessage(); // Mensaje del SQL backend
        String detalle = "Error de integridad: ";

        if (mensajeOriginal.contains("Duplicate entry")) {
            // Extraer el valor duplicado y el índice afectado
            String[] partes = mensajeOriginal.split("'");
            if (partes.length > 2) {
                String valorDuplicado = partes[1];
                String campo = partes[3];
                detalle += "El valor '" + valorDuplicado + "' ya se encuentra registrado.";
            }
        } else {
            detalle += mensajeOriginal;
        }

        throw new ValidacionException(detalle);
    }

}
