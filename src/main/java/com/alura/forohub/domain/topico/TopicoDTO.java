package com.alura.forohub.domain.topico;

import com.alura.forohub.domain.curso.Curso;
import com.alura.forohub.domain.usuario.Usuario;

import java.time.LocalDateTime;

public record  TopicoDTO(
        int id,
        String titulo,
        String autorNombre,
        String cursoNombre,
        LocalDateTime creacion,
        Status status,
        String mensaje,
        int respuestas
) {
}
