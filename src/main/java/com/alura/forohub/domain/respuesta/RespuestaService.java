package com.alura.forohub.domain.respuesta;

import com.alura.forohub.domain.topico.Status;
import com.alura.forohub.domain.topico.Topico;
import com.alura.forohub.domain.topico.TopicoRepository;
import com.alura.forohub.domain.usuario.Usuario;
import com.alura.forohub.domain.usuario.UsuarioRepository;
import com.alura.forohub.infra.errores.ValidacionDeIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class RespuestaService {
    @Autowired
    private RespuestaRepository respuestaRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public RespuestaDTO crearRespuesta(RespuestaDatosCrear datosCrear, UriComponentsBuilder uri) {
        Usuario usuario = usuarioRepository.getReferenceById(datosCrear.id_autor());
        Topico topico = topicoRepository.getReferenceById(datosCrear.id_topico());
        if (topico.getStatus() == Status.CERRADO || !topico.isActivo() || !usuario.isActivo()) {
            throw new ValidacionDeIntegridad("Datos inválidos para la creación de respuesta");
        }
        topico.setRespuestas(topico.getRespuestas() + 1);
        Respuesta respuesta = new Respuesta(datosCrear, usuario, topico);
        respuestaRepository.save(respuesta);
        URI url = uri.path("/respuesta/{id}").buildAndExpand(respuesta.getId()).toUri();
        return convertirDto(respuesta);
    }

    public RespuestaDTO obtenerRespuesta(int id) {
        Respuesta respuesta = respuestaRepository.getReferenceById(id);
        if (!respuesta.isActivo()) {
            throw new ValidacionDeIntegridad("Respuesta no encontrada");
        }
        return convertirDto(respuesta);
    }

    public List<RespuestaDTO> obtenerRespuestasPorTopico(int id) {
        List<Respuesta> respuestas = respuestaRepository.findByTopicoId(id);
        return respuestas.stream()
                .filter(Respuesta::isActivo)
                .map(this::convertirDto)
                .toList();
    }

    public RespuestaDTO actualizarRespuesta(int id, RespuestaActualizarMensaje mensaje) {
        Respuesta respuesta = respuestaRepository.getReferenceById(id);
        if (!respuesta.isActivo()) {
            throw new ValidacionDeIntegridad("Respuesta no encontrada");
        }
        respuesta.setMensaje(mensaje.mensaje());
        respuesta.setFechaCreacion(LocalDateTime.now());
        return convertirDto(respuesta);
    }

    public RespuestaDTO ponerSolucionRespuesta(int id) {
        Respuesta respuesta = respuestaRepository.getReferenceById(id);
        if (!respuesta.isActivo()) {
            throw new ValidacionDeIntegridad("Respuesta no encontrada");
        }
        respuesta.setSolucion(true);
        return convertirDto(respuesta);
    }

    public void borrarRespuesta(int id) {
        Respuesta respuesta = respuestaRepository.getReferenceById(id);
        if (!respuesta.isActivo()) {
            throw new ValidacionDeIntegridad("Respuesta no encontrada");
        }
        respuesta.eliminarRespuesta();
        respuesta.getTopico().setRespuestas(respuesta.getTopico().getRespuestas() - 1);
    }

    private RespuestaDTO convertirDto(Respuesta respuesta) {
        return new RespuestaDTO(respuesta.getId(), respuesta.getAutor().getNombre(), respuesta.getMensaje(), respuesta.isSolucion());
    }
}
