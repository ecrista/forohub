package com.aluralatamcursos.forohub.controllers;

import com.aluralatamcursos.forohub.domain.usuario.DatosAutenticacion;
import com.aluralatamcursos.forohub.domain.usuario.Usuario;
import com.aluralatamcursos.forohub.infra.security.DatosJWTToken;
import com.aluralatamcursos.forohub.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacionController {

    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity realizarLogin(@RequestBody @Valid DatosAutenticacion datos) {
        Authentication authToken = new UsernamePasswordAuthenticationToken(datos.email(), datos.contrasena());
        var usuarioAutentificado = manager.authenticate(authToken);
        var JWTtoken = tokenService.generarToken((Usuario) usuarioAutentificado.getPrincipal());


        return ResponseEntity.ok(new DatosJWTToken(JWTtoken));
    }
}