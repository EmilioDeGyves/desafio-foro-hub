package com.alura.forohub.domain.topico;

import jakarta.validation.constraints.NotNull;

public record TopicoDatoActualizar(
        String mensaje,
        String titulo,
        Status status
) {
}
