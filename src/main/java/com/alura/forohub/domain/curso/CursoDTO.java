package com.alura.forohub.domain.curso;

import jakarta.validation.constraints.NotNull;

public record CursoDTO(
        @NotNull
        String nombre,
        @NotNull
        Categoria categoria
) {
}
