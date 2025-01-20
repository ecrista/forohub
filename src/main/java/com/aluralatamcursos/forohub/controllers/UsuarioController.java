package com.aluralatamcursos.forohub.controllers;

import com.aluralatamcursos.forohub.domain.topico.DatosActualizarTopico;
import com.aluralatamcursos.forohub.domain.topico.DatosRespuestaTopico;
import com.aluralatamcursos.forohub.domain.usuario.*;
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
@RequestMapping("usuarios")
@SecurityRequirement(name = "bearer-key")
public class UsuarioController {
    @Autowired
    private OperacionesUsuario operacionesUsuario;

    @PostMapping
    @Transactional
    public ResponseEntity registrarUsuario(@RequestBody @Valid DatosRegistroUsuario datosRegistroUsuario, UriComponentsBuilder uriComponentsBuilder){
        DatosRespuestaUsuario usuario = operacionesUsuario.crearNuevoUsuario(datosRegistroUsuario);
        URI url = uriComponentsBuilder.path("/usuarios/{id}").buildAndExpand(usuario.id()).toUri();
        return ResponseEntity.created(url).body(usuario);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoUsuario>> mostrarListaUsuarios(@PageableDefault(page = 0, size = 10, sort = {"nombre"}, direction = ASC) org.springframework.data.domain.Pageable paginacion){
        return ResponseEntity.ok(operacionesUsuario.consultarListaUsuarios(paginacion));
    }

    @GetMapping("/{id}")
    public ResponseEntity consultarDatosUsuario(@PathVariable Long id){
        DatosRespuestaUsuario topico = operacionesUsuario.consultarUsuario(id);
        return ResponseEntity.ok(topico);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity actualizarDatosUsuario(@PathVariable Long id,@RequestBody @Valid DatosActualizarUsuario datos){
        DatosRespuestaUsuarioPrivado usuario = operacionesUsuario.actualizarUsuario(id,datos);
        return ResponseEntity.ok(usuario);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarUsuario(@PathVariable Long id){
        operacionesUsuario.borrarUsuario(id);
        return ResponseEntity.noContent().build();
    }

}
