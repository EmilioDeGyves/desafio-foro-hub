package com.alura.forohub.domain.respuesta;

import com.alura.forohub.domain.curso.Curso;
import com.alura.forohub.domain.topico.Topico;
import com.alura.forohub.domain.topico.TopicoDatosCrear;
import com.alura.forohub.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "respuesta")
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Respuesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    @Setter
    private String mensaje;

    @Column(nullable = false)
    @Setter
    private LocalDateTime fechaCreacion;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "autor", nullable = false)
    private Usuario autor;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "topico", nullable = false)
    private Topico topico;

    @Setter
    @Column(nullable = false)
    private boolean solucion;

    private boolean activo;

    // getters y setters

    public Respuesta(RespuestaDatosCrear datos, Usuario autor, Topico topico){
        this.mensaje = datos.mensaje();
        this.autor = autor;
        this.topico = topico;
        this.fechaCreacion = LocalDateTime.now();
        this.solucion = false;
        this.activo = true;
    }

    public void eliminarRespuesta() {
        this.activo = false;
    }
}
