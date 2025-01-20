package com.aluralatamcursos.forohub.controllers;

import com.aluralatamcursos.forohub.domain.topico.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.awt.print.Pageable;
import java.net.URI;

import static org.springframework.data.domain.Sort.Direction.ASC;

@RestController
@RequestMapping("topicos")
@SecurityRequirement(name = "bearer-key")
public class TopicoController {

    @Autowired
    private OperacionesTopicos operacionesTopicos;

    @PostMapping
    @Transactional
    public ResponseEntity registrarTopico(@RequestBody @Valid DatosRegistroTopico datosRegistroTopico, UriComponentsBuilder uriComponentsBuilder){
        DatosRespuestaTopico topico = operacionesTopicos.crearNuevoTopico(datosRegistroTopico);
        URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.id()).toUri();
        return ResponseEntity.created(url).body(topico);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoTopico>> listarTopicos(@PageableDefault(page = 0, size = 10, sort = {"fechaCreacion"}, direction = ASC) org.springframework.data.domain.Pageable paginacion){
            return ResponseEntity.ok(operacionesTopicos.consultarListaTopicos(paginacion));
    }

    @GetMapping("/{id}")
    public ResponseEntity consultarDatosTopico(@PathVariable Long id){
        DatosRespuestaTopico topico = operacionesTopicos.consultarTopico(id);
        return ResponseEntity.ok(topico);
    }
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity actualizarDatosTopico(@PathVariable Long id,@RequestBody @Valid DatosActualizarTopico datos){
        DatosRespuestaTopico topico = operacionesTopicos.actualizarTopico(id,datos);
        return ResponseEntity.ok(topico);
    }
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarTopico(@PathVariable Long id){
        operacionesTopicos.borrarTopico(id);
        return ResponseEntity.noContent().build();
    }



}
