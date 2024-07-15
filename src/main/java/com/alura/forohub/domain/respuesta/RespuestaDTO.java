package com.alura.forohub.domain.respuesta;

public record RespuestaDTO(
        int id,
        String autor,
        String mensaje,
        boolean solucion
) {
}
