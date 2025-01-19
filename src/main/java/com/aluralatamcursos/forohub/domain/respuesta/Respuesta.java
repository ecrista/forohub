package com.aluralatamcursos.forohub.domain.respuesta;

import com.aluralatamcursos.forohub.domain.topico.Topico;
import com.aluralatamcursos.forohub.domain.usuario.Usuario;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Table(name = "respuestas")
@Entity(name = "Respuesta")
public class Respuesta {
    @Id
    @GeneratedValue
    private Long id;
    private String mensaje;
    private LocalDateTime fechaCreacion;
    private String solucion;
    @ManyToOne
    @JoinColumn(name = "topicos_id")
    private Topico topico;
    @ManyToOne
    @JoinColumn(name = "usuarios_id")
    private Usuario autor;

    public Respuesta() {
    }

    public Usuario getAutor() {
        return autor;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }

    public Topico getTopico() {
        return topico;
    }

    public void setTopico(Topico topico) {
        this.topico = topico;
    }

    public String getSolucion() {
        return solucion;
    }

    public void setSolucion(String solucion) {
        this.solucion = solucion;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
