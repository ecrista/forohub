package com.aluralatamcursos.forohub.domain.topico;

import com.aluralatamcursos.forohub.domain.ValidacionException;
import com.aluralatamcursos.forohub.domain.curso.Curso;
import com.aluralatamcursos.forohub.domain.curso.CursoRepository;
import com.aluralatamcursos.forohub.domain.usuario.Usuario;
import com.aluralatamcursos.forohub.domain.usuario.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Optional;

@Service
public class OperacionesTopicos {

    @Autowired
    TopicoRepository topicoRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    CursoRepository cursoRepository;

    public DatosRespuestaTopico crearNuevoTopico(DatosRegistroTopico datosRegistroTopico) {
        Optional<Curso> curso = cursoRepository.findByNombre(datosRegistroTopico.nombreCurso());
        Optional<Usuario> usuario = usuarioRepository.findById(datosRegistroTopico.idUsuario());
        if (curso.isPresent()){
        }else {
            throw new ValidacionException("No existe el curso que se proporciono");
        }
        if (usuario.isPresent()){
        }else {
            throw new ValidacionException("No existe un usuario con el id proporcionado");
        }
        try {
            Topico topico = topicoRepository.save(new Topico(datosRegistroTopico,curso.get(),usuario.get()));
            return new DatosRespuestaTopico(topico);
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
                    detalle += "El valor '" + valorDuplicado + "' ya se encuentra registrado.";
                }
            } else {
                detalle += mensajeOriginal;
            }

            throw new ValidacionException(detalle);
        }


    }

    public Page<DatosListadoTopico> consultarListaTopicos(Pageable paginacion) {
        return topicoRepository.findAllByStatusNotELIMINADO(paginacion).map(DatosListadoTopico::new);
    }

    public DatosRespuestaTopico consultarTopico(Long id) {
        Optional<Topico> topico = topicoRepository.findById(id);
        if (topico.isPresent()){
            return new DatosRespuestaTopico(topico.get());
        }else {
            throw new ValidacionException("No existe un topico con el id informado");
        }
    }

    public DatosRespuestaTopico actualizarTopico(Long id, DatosActualizarTopico datos) {
        Optional<Topico> topico = topicoRepository.findById(id);
        if (topico.isPresent()){
            try {
                Topico topicoExistente=topico.get();
                Optional<Curso> curso=null;
                if (datos.nombreCurso()!=null){
                    curso = cursoRepository.findByNombre(datos.nombreCurso());
                    if (!curso.isPresent()){
                        throw new ValidacionException("No existe el curso que se proporciono");
                    }
                }
                topicoExistente.actualizarDatos(datos,curso);
                return new DatosRespuestaTopico(topicoExistente);
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
                        detalle += "El valor '" + valorDuplicado + "' ya se encuentra registrado.";
                    }
                } else {
                    detalle += mensajeOriginal;
                }

                throw new ValidacionException(detalle);
            }
        }else {
            throw new ValidacionException("No existe el Id del Topico que se proporciono");
        }
    }

    public void borrarTopico(Long id) {
        var topico = topicoRepository.findById(id);
        if (topico.isPresent()){
            var topicoExiste=topico.get();
            topicoExiste.desactivarTopico();
        }
        else{
            throw new ValidacionException("No existe el Id del Topico que se proporciono");
        }
    }
}
