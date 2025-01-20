package com.aluralatamcursos.forohub.controllers;

import com.aluralatamcursos.forohub.domain.respuesta.*;
import com.aluralatamcursos.forohub.domain.respuesta.DatosListadoRespuesta;
import com.aluralatamcursos.forohub.domain.respuesta.DatosRegistroRespuesta;
import com.aluralatamcursos.forohub.domain.respuesta.DatosRespuestaRespuesta;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static org.springframework.data.domain.Sort.Direction.ASC;

@RestController
@RequestMapping("respuestas")
@SecurityRequirement(name = "bearer-key")
public class RespuestaController {

    @Autowired
    private OperacionesRespuestas operacionesRespuestas;


    @PostMapping
    @Transactional
    public ResponseEntity registrarRespuesta(@RequestBody @Valid DatosRegistroRespuesta datosRegistroRespuesta, UriComponentsBuilder uriComponentsBuilder){
        DatosRespuestaRespuesta respuesta = operacionesRespuestas.crearNuevoRespuesta(datosRegistroRespuesta);
        URI url = uriComponentsBuilder.path("/respuestas/{id}").buildAndExpand(respuesta.id()).toUri();
        return ResponseEntity.created(url).body(respuesta);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoRespuesta>> listarRespuestas(@PageableDefault(page = 0, size = 10, sort = {"fechaCreacion"}, direction = ASC) org.springframework.data.domain.Pageable paginacion){
        return ResponseEntity.ok(operacionesRespuestas.consultarListaRespuestas(paginacion));
    }

    @GetMapping("/{id}")
    public ResponseEntity consultarDatosRespuesta(@PathVariable Long id){
        DatosRespuestaRespuesta respuesta = operacionesRespuestas.consultarRespuesta(id);
        return ResponseEntity.ok(respuesta);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity actualizarDatosRespuesta(@PathVariable Long id,@RequestBody @Valid DatosActualizarRespuesta datos){
        DatosRespuestaRespuesta respuesta = operacionesRespuestas.actualizarRespuesta(id,datos);
        return ResponseEntity.ok(respuesta);
    }
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarRespuesta(@PathVariable Long id){
        operacionesRespuestas.borrarRespuesta(id);
        return ResponseEntity.noContent().build();
    }

}
