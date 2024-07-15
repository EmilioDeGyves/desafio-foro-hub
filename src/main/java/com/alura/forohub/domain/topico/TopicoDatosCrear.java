package com.alura.forohub.domain.topico;

import jakarta.validation.constraints.NotNull;

public record TopicoDatosCrear(
        @NotNull
        String titulo,
        @NotNull
        int id_usuario,
        @NotNull
        String mensaje,
        @NotNull
        int id_curso
){


}
