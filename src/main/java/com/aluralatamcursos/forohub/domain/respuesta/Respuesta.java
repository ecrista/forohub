package com.aluralatamcursos.forohub.domain.respuesta;

import com.aluralatamcursos.forohub.domain.topico.Estado;
import com.aluralatamcursos.forohub.domain.topico.Topico;
import com.aluralatamcursos.forohub.domain.usuario.Usuario;
import jakarta.persistence.*;
import jakarta.validation.Valid;

import java.time.LocalDateTime;
import java.util.Optional;

@Table(name = "respuestas")
@Entity(name = "Respuesta")
public class Respuesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mensaje;
    private LocalDateTime fechaCreacion;
    private String solucion;
    @Enumerated(EnumType.STRING)
    private Estado status;
    @ManyToOne
    @JoinColumn(name = "topicos_id")
    private Topico topico;
    @ManyToOne
    @JoinColumn(name = "usuarios_id")
    private Usuario autor;

    public Respuesta() {
    }

    public Respuesta(DatosRegistroRespuesta datosRegistroRespuesta, Topico topico, Usuario usuario) {
        this.autor = usuario;
        this.topico = topico;
        this.status = Estado.ACTIVO;
        this.solucion = datosRegistroRespuesta.solucion();
        this.fechaCreacion = LocalDateTime.now();
        this.mensaje = datosRegistroRespuesta.mensaje();
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

    public Estado getStatus() {
        return status;
    }

    public void setStatus(Estado status) {
        this.status = status;
    }

    public void actualizarDatos(DatosActualizarRespuesta datos, Optional<Topico> topico) {
        if (datos.mensaje() != null)
            this.mensaje = datos.mensaje();
        if (datos.solucion() != null)
            this.solucion = datos.solucion();
        if (topico != null && topico.isPresent())
            this.topico = topico.get();
    }
    public void desactivarRespuesta() {
        this.status=Estado.ELIMINADO;
    }

}
