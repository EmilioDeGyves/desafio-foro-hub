package com.alura.forohub.controladores;

import com.alura.forohub.domain.usuario.DatosAutenticacionUsuario;
import com.alura.forohub.domain.usuario.Usuario;
import com.alura.forohub.infra.security.DatosToken;
import com.alura.forohub.infra.security.TokenService;
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
public class AuthController {
    @Autowired
    private AuthenticationManager auth;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity autenticarUsuario(@RequestBody @Valid DatosAutenticacionUsuario user){
        Authentication authToken = new UsernamePasswordAuthenticationToken(user.usuario(), user.clave());
        var usuarioAutenticado = auth.authenticate(authToken);
        var jwToken = tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal());

        return ResponseEntity.ok(new DatosToken(jwToken));
    }
}
