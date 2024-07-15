package com.alura.forohub.domain.usuario;

import java.util.List;

public record UsuarioDatosCrear(
        int id,
        String nombre,
        List<String> perfil
) {
}
