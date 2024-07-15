package com.alura.forohub.controladores;

import com.alura.forohub.domain.respuesta.*;
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
@RequestMapping("/respuesta")
public class RespuestaController {
    @Autowired
    private RespuestaService service;

    @PostMapping
    public ResponseEntity<RespuestaDTO> crearRespuesta(@RequestBody @Valid RespuestaDatosCrear datosCrear, UriComponentsBuilder uri) {
        RespuestaDTO respuestaDTO = service.crearRespuesta(datosCrear, uri);
        URI url = uri.path("/respuesta/{id}").buildAndExpand(respuestaDTO.id()).toUri();
        return ResponseEntity.created(url).body(respuestaDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RespuestaDTO> obtenerRespuesta(@PathVariable int id) {
        return ResponseEntity.ok(service.obtenerRespuesta(id));
    }

    @GetMapping("/topico/{id}")
    public ResponseEntity<List<RespuestaDTO>> obtenerRespuestasPorTopico(@PathVariable int id) {
        return ResponseEntity.ok(service.obtenerRespuestasPorTopico(id));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<RespuestaDTO> actualizarRespuesta(@PathVariable int id, @RequestBody RespuestaActualizarMensaje mensaje) {
        return ResponseEntity.ok(service.actualizarRespuesta(id, mensaje));
    }

    @PutMapping("/solucion/{id}")
    @Transactional
    public ResponseEntity<RespuestaDTO> ponerSolucionRespuesta(@PathVariable int id) {
        return ResponseEntity.ok(service.ponerSolucionRespuesta(id));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> borrarRespuesta(@PathVariable int id) {
        service.borrarRespuesta(id);
        return ResponseEntity.noContent().build();
    }
}
