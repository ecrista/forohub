package com.aluralatamcursos.forohub.domain.perfil;

import com.aluralatamcursos.forohub.domain.usuario.Usuario;
import jakarta.persistence.*;

import java.util.List;

@Table(name ="perfiles")
@Entity(name = "Perfil")
public class Perfil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String  nombre;
    @ManyToMany(mappedBy = "perfiles")
    private List<Usuario> usuarios;

    public Perfil() {
    }

    public Perfil(Perfil perfil) {
        this.id= perfil.id;
        this.nombre=perfil.nombre;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
