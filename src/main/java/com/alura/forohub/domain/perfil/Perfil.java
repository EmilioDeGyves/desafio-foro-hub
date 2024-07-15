package com.alura.forohub.domain.perfil;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "perfil")
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Perfil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private boolean activo;

    public Perfil(PerfilDTO datos){
        this.nombre = datos.nombre();
        this.activo = true;
    }

    @Override
    public String toString() {
        return "perfil: '" + nombre + '\'' +
                ", id: " + id;
    }
}
