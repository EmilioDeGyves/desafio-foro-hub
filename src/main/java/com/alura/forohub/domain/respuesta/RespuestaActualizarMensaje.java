package com.alura.forohub.domain.respuesta;

import jakarta.validation.constraints.NotNull;

public record RespuestaActualizarMensaje(
        @NotNull
        String mensaje
) {
}
