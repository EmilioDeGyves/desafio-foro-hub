package com.alura.forohub.domain.topico;

import com.alura.forohub.domain.curso.Curso;
import com.alura.forohub.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name="topico", uniqueConstraints = {
        @UniqueConstraint(columnNames = "titulo"),
        @UniqueConstraint(columnNames = "mensaje")
})
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String titulo;

    @Column(nullable = false, unique = true)
    private String mensaje;

    @Column(nullable = false)
    private LocalDateTime fechaCreacion;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "autor", nullable = false)
    private Usuario autor;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "curso", nullable = false)
    private Curso curso;

    @Column(nullable = false)
    private int respuestas;

    @Column(nullable = false)
    private boolean activo;

    public Topico(TopicoDatosCrear datos, Usuario autor, Curso curso) {
        this.titulo = datos.titulo();
        this.mensaje = datos.mensaje();
        this.fechaCreacion = LocalDateTime.now();
        this.status = Status.ABIERTO;  // Ajustar según tu enum Status
        this.autor = autor;
        this.curso = curso;
        this.respuestas = 0;
        this.activo = true;
    }

    public void eliminarTopico(){
        this.activo = false;
    }
}
