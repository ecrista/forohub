package com.aluralatamcursos.forohub.domain.topico;

import com.aluralatamcursos.forohub.domain.curso.Curso;
import com.aluralatamcursos.forohub.domain.respuesta.Respuesta;
import com.aluralatamcursos.forohub.domain.usuario.Usuario;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Table(name = "topicos")
@Entity(name = "Topico")
@EqualsAndHashCode(of = "id")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    private LocalDateTime fechaCreacion;
    @Enumerated(EnumType.STRING)
    private Estado status;
    @ManyToOne
    @JoinColumn(name = "usuarios_id")
    private Usuario autor;
    @ManyToOne
    @JoinColumn(name = "cursos_id")
    private Curso curso;
    @OneToMany(mappedBy = "topico")
    private List<Respuesta> respuestas;

    public Topico() {
    }

    public Topico( DatosRegistroTopico datosRegistroTopico,Curso curso, Usuario usuario) {
        this.curso = curso;
        this.autor = usuario;
        this.status = Estado.ACTIVO;
        this.fechaCreacion = LocalDateTime.now();
        this.mensaje = datosRegistroTopico.mensaje();
        this.titulo = datosRegistroTopico.titulo();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Estado getStatus() {
        return status;
    }

    public void setStatus(Estado status) {
        this.status = status;
    }

    public Usuario getAutor() {
        return autor;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public List<Respuesta> getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(List<Respuesta> respuestas) {
        this.respuestas = respuestas;
    }

    public void actualizarDatos(DatosActualizarTopico datosActualizarTopico, Optional<Curso> cursoActualizar){
        if (cursoActualizar!=null && cursoActualizar.isPresent())
            this.curso=cursoActualizar.get();
        if (datosActualizarTopico.mensaje()!=null)
            this.mensaje= datosActualizarTopico.mensaje();
        if (datosActualizarTopico.titulo()!=null)
            this.titulo=datosActualizarTopico.titulo();
    }

    public void desactivarTopico() {
        this.status=Estado.ELIMINADO;
    }
}
