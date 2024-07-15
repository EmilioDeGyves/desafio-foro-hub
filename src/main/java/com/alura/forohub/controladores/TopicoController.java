package com.alura.forohub.controladores;

import com.alura.forohub.domain.topico.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Controller
@RequestMapping("/topico")
public class TopicoController {

    @Autowired
    private TopicoService service;

    @PostMapping
    public ResponseEntity<TopicoDTO> crearTopico(@RequestBody @Valid TopicoDatosCrear datos, UriComponentsBuilder uri) {
        TopicoDTO respuesta = service.crearTopico(datos);
        URI url = uri.path("/topico/{id}").buildAndExpand(respuesta.id()).toUri();
        return ResponseEntity.created(url).body(respuesta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicoDTO> getTopico(@PathVariable int id){
        return ResponseEntity.ok(service.obtenerTopico(id));
    }

    @GetMapping
    public ResponseEntity<List<TopicoDTO>> listarTopicos(){
        return ResponseEntity.ok(service.listarTopicos());
    }

    @GetMapping("/abierto")
    public ResponseEntity<List<TopicoDTO>> listarTopicosAbiertos(){
        return ResponseEntity.ok(service.listarTopicosAbiertos());
    }

    @GetMapping("/cerrado")
    public ResponseEntity<List<TopicoDTO>> listarTopicosCerrados(){
        return ResponseEntity.ok(service.listarTopicosCerrados());
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<TopicoDTO> actualizarTopico(@RequestBody TopicoDatoActualizar datos, @PathVariable int id){
        return ResponseEntity.ok(service.actualizarTopico(datos, id));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity borrarTopico(@PathVariable int id){
        service.borrarTopico(id);
        return ResponseEntity.noContent().build();
    }

    public TopicoDTO generarTopicoDTO(Topico topico){
        return new TopicoDTO(topico.getId(), topico.getTitulo(), topico.getAutor().getNombre(), topico.getCurso().getNombre(),topico.getFechaCreacion(),topico.getStatus(),topico.getMensaje(),topico.getRespuestas());
    }
}