package com.alura.forohub.controladores;

import com.alura.forohub.domain.perfil.Perfil;
import com.alura.forohub.domain.perfil.PerfilRepository;
import com.alura.forohub.domain.usuario.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/usuario")
public class UserController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PerfilRepository perfilRepository;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    @Transactional
    public ResponseEntity<UsuarioDatosCrear> crearUsuario(@RequestBody UsuarioDTO usuarioDTO, UriComponentsBuilder uri) {
        UsuarioDatosCrear respuesta = usuarioService.obtenerUsuarios(usuarioDTO);
        URI url = uri.path("/usuario/{id}").buildAndExpand(respuesta.id()).toUri();
        return ResponseEntity.created(url).body(respuesta);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDatosCrear>> obtenerUsuarios(){
        return ResponseEntity.ok(usuarioService.obenerListaUsuarios());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDatosCrear> obtenerUsuario(@PathVariable int id){
        return ResponseEntity.ok(usuarioService.obtenerUsuario(id));
    }
}
