package com.aluralatamcursos.forohub.domain.usuario;

import com.aluralatamcursos.forohub.domain.perfil.Perfil;
import com.aluralatamcursos.forohub.domain.respuesta.Respuesta;
import com.aluralatamcursos.forohub.domain.topico.Topico;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collection;
import java.util.List;

@Table(name = "usuarios")
@Entity(name ="Usuario")
@EqualsAndHashCode(of = "id")
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String correoElectronico;
    private String contrasena;
    private Boolean activo;
    @ManyToMany
    @JoinTable(name = "usuarios_has_perfiles",joinColumns = @JoinColumn(name = "usuarios_id"),inverseJoinColumns = @JoinColumn(name = "perfiles_id"))
    private List<Perfil> perfiles;
    @OneToMany(mappedBy = "autor")
    private List<Topico> topicos;
    @OneToMany(mappedBy = "autor")
    private List<Respuesta> respuestas;

    public Usuario() {
    }

    public Usuario(DatosRegistroUsuario datosRegistroUsuario, List<Perfil> listaPerfiles) {
        this.nombre = datosRegistroUsuario.nombre();
        this.correoElectronico = datosRegistroUsuario.email();
        this.contrasena = generarContrasena(datosRegistroUsuario.contrasena());
        this.activo = true;
        this.perfiles = listaPerfiles;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
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
    public List<Perfil> getPerfiles() {
        return perfiles;
    }

    public void setPerfiles(List<Perfil> perfiles) {
        this.perfiles = perfiles;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public List<Topico> getTopicos() {
        return topicos;
    }

    public void setTopicos(List<Topico> topicos) {
        this.topicos = topicos;
    }

    public List<Respuesta> getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(List<Respuesta> respuestas) {
        this.respuestas = respuestas;
    }

    public String generarContrasena(String pssw) {
        var pass=new BCryptPasswordEncoder().encode(pssw);
        return pass;
    }

    @Override
    public String getPassword() {
        return contrasena;
    }

    @Override
    public String getUsername() {
        return correoElectronico;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
