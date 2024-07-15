package com.alura.forohub.domain.topico;

import com.alura.forohub.domain.curso.Curso;
import com.alura.forohub.domain.curso.CursoRepository;
import com.alura.forohub.domain.usuario.Usuario;
import com.alura.forohub.domain.usuario.UsuarioRepository;
import com.alura.forohub.infra.errores.ValidacionDeIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TopicoService {
    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    public TopicoDTO crearTopico(TopicoDatosCrear datos){
        Curso curso = cursoRepository.getReferenceById(datos.id_curso());
        Usuario usuario = usuarioRepository.getReferenceById(datos.id_usuario());
        Topico topico = new Topico(datos, usuario, curso);
        TopicoDTO respuesta = generarTopicoDTO(topico);
        topicoRepository.save(topico);
        return respuesta;
    }

    public TopicoDTO obtenerTopico(int id){
        Topico topico = topicoRepository.getReferenceById(id);
        if(!topico.isActivo()){
            throw new ValidacionDeIntegridad("topico no encontrado");
        }
        return generarTopicoDTO(topico);
    }

    public List<TopicoDTO> listarTopicos(){
        List<Topico> topicos = topicoRepository.findAll();
        return topicos.stream()
                .filter(Topico::isActivo)
                .map(this::generarTopicoDTO)
                .toList();
    }

    public List<TopicoDTO> listarTopicosAbiertos(){
        List<Topico> topicos = topicoRepository.findAll();
        return topicos.stream()
                .filter(topico -> topico.isActivo() && topico.getStatus() == Status.ABIERTO)
                .map(this::generarTopicoDTO)
                .toList();
    }

    public List<TopicoDTO> listarTopicosCerrados(){
        List<Topico> topicos = topicoRepository.findAll();
        return topicos.stream()
                .filter(topico -> topico.isActivo() && topico.getStatus() == Status.CERRADO)
                .map(this::generarTopicoDTO)
                .toList();
    }

    public TopicoDTO actualizarTopico(TopicoDatoActualizar datos, int id){
        Topico topico = topicoRepository.getReferenceById(id);
        if (!topico.isActivo()){
            throw new ValidacionDeIntegridad("topico no encontrado");
        }
        if (datos.mensaje() != null) {
            topico.setMensaje(datos.mensaje());
        }
        if (datos.titulo() != null) {
            topico.setTitulo(datos.titulo());
        }
        if (datos.status() != null) {
            topico.setStatus(datos.status());
        }
        topico.setFechaCreacion(LocalDateTime.now());
        return generarTopicoDTO(topico);
    }

    public void borrarTopico(int id){
        Topico respuesta = topicoRepository.getReferenceById(id);
        if(!respuesta.isActivo()){
            throw new ValidacionDeIntegridad("topico no encontrado o ya fue borrado");
        }
        respuesta.eliminarTopico();
    }

    private TopicoDTO generarTopicoDTO(Topico topico){
        return new TopicoDTO(topico.getId(),topico.getTitulo(), topico.getAutor().getNombre(), topico.getCurso().getNombre(),topico.getFechaCreacion(),topico.getStatus(),topico.getMensaje(),topico.getRespuestas());
    }

}
