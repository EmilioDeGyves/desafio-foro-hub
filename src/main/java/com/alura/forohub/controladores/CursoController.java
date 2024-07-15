package com.alura.forohub.controladores;

import com.alura.forohub.domain.curso.Curso;
import com.alura.forohub.domain.curso.CursoDTO;
import com.alura.forohub.domain.curso.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Controller
@RequestMapping("/curso")
public class CursoController {
    @Autowired
    CursoRepository cursoRepository;

    @PostMapping
    public ResponseEntity<CursoDTO> crearCurso(@RequestBody CursoDTO datos, UriComponentsBuilder uri){
        Curso curso = new Curso(datos);
        CursoDTO respuesta = new CursoDTO(datos.nombre(), datos.categoria());
        cursoRepository.save(curso);
        URI url = uri.path("/curso/{id}").buildAndExpand(curso.getId()).toUri();
        return ResponseEntity.created(url).body(respuesta);
    }

    @GetMapping("/{id}")
    public  ResponseEntity<CursoDTO> obtenerCurso(@PathVariable int id){
        Curso curso = cursoRepository.getReferenceById(id);
        return ResponseEntity.ok(new CursoDTO(curso.getNombre(),curso.getCategoria()));
    }

}
