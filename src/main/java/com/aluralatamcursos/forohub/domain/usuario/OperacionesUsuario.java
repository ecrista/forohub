package com.aluralatamcursos.forohub.domain.usuario;

import com.aluralatamcursos.forohub.domain.ValidacionException;
import com.aluralatamcursos.forohub.domain.perfil.Perfil;
import com.aluralatamcursos.forohub.domain.perfil.PerfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OperacionesUsuario {
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    PerfilRepository perfilRepository;

    public DatosRespuestaUsuario crearNuevoUsuario(DatosRegistroUsuario datosRegistroUsuario) {
        List<Perfil> listaPerfiles=datosRegistroUsuario.perfiles().stream().map(p->{
            Optional<Perfil> perfil=perfilRepository.findByNombre(p);
            if (!perfil.isPresent())
                throw new ValidacionException("No existe el perfil "+p+" que se proporciono");
            return new Perfil(perfil.get());
        }).toList();

        try {
            Usuario usuario = usuarioRepository.save(new Usuario(datosRegistroUsuario,listaPerfiles));
            return new DatosRespuestaUsuario(usuario);
        }catch (DataIntegrityViolationException e) {
            // Analizar el mensaje de la excepción para obtener detalles
            String mensajeOriginal = e.getRootCause().getMessage(); // Mensaje del SQL backend
            String detalle = "Error de integridad: ";

            if (mensajeOriginal.contains("Duplicate entry")) {
                // Extraer el valor duplicado y el índice afectado
                String[] partes = mensajeOriginal.split("'");
                if (partes.length > 2) {
                    String valorDuplicado = partes[1];
                    String campo = partes[3];
                    detalle += "El valor usuario '" + valorDuplicado + "' ya se encuentra registrado.";
                }
            } else {
                detalle += mensajeOriginal;
            }

            throw new ValidacionException(detalle);
        }


    }

    public Page<DatosListadoUsuario> consultarListaUsuarios(Pageable paginacion) {
        return usuarioRepository.findByActivoTrue(paginacion).map(DatosListadoUsuario::new);
    }

    public DatosRespuestaUsuario consultarUsuario(Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (usuario.isPresent()){
            return new DatosRespuestaUsuario(usuario.get());
        }else {
            throw new ValidacionException("No existe un usuario con el id proporcionado");
        }
    }

    public DatosRespuestaUsuarioPrivado actualizarUsuario(Long id, DatosActualizarUsuario datos) {

        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (usuario.isPresent()){
            try {
                Usuario usuarioExistente=usuario.get();
                List<Perfil> listaPerfiles= new ArrayList<>();
                if (datos.perfiles()!=null && !datos.perfiles().isEmpty()){
                    listaPerfiles=datos.perfiles().stream().map(p->{
                        Optional<Perfil> perfil=perfilRepository.findByNombre(p);
                        if (!perfil.isPresent())
                            throw new ValidacionException("No existe el perfil "+p+" que se proporciono");
                        return new Perfil(perfil.get());
                    }).toList();
                }
                usuarioExistente.actualizarDatos(datos,listaPerfiles);
                return new DatosRespuestaUsuarioPrivado(usuarioExistente);
            }catch (DataIntegrityViolationException e) {
                throw new ValidacionException(e);
            }
        }else {
            throw new ValidacionException("No existe el Id del usuario que se proporciono");
        }
    }

    public void borrarUsuario(Long id) {
        var usuario = usuarioRepository.findById(id);
        if (usuario.isPresent()){
            var usuarioExiste=usuario.get();
            usuarioExiste.desactivarUsuario();
        }
        else{
            throw new ValidacionException("No existe el Id del Usuario que se proporciono");
        }
    }
}
