package com.alura.forohub.domain.perfil;

import jakarta.validation.constraints.NotNull;

public record PerfilDTO(@NotNull String nombre) {
}
