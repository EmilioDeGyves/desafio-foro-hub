package com.alura.forohub.domain.respuesta;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RespuestaRepository extends JpaRepository<Respuesta, Integer> {
    List<Respuesta> findByTopicoId(int id);
}
