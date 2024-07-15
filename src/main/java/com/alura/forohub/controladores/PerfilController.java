package com.alura.forohub.controladores;

import com.alura.forohub.domain.perfil.Perfil;
import com.alura.forohub.domain.perfil.PerfilDTO;
import com.alura.forohub.domain.perfil.PerfilRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/perfil")
public class PerfilController {
    @Autowired
    private PerfilRepository repository;

    @PostMapping
    public ResponseEntity<Perfil> agregarPerfil(@RequestBody @Valid PerfilDTO datos){
        Perfil perfil = new Perfil(datos);
        repository.save(perfil);
        return ResponseEntity.ok(perfil);
    }

}
