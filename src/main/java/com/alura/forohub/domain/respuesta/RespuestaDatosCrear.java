package com.alura.forohub.domain.respuesta;

import jakarta.validation.constraints.NotNull;

public record RespuestaDatosCrear(
        @NotNull
        String mensaje,
        @NotNull
        int id_topico,
        @NotNull
        int id_autor
) {
}
