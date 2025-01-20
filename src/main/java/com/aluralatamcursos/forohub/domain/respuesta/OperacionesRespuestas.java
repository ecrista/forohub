package com.aluralatamcursos.forohub.domain.respuesta;

import com.aluralatamcursos.forohub.domain.ValidacionException;
import com.aluralatamcursos.forohub.domain.curso.Curso;
import com.aluralatamcursos.forohub.domain.topico.DatosListadoTopico;
import com.aluralatamcursos.forohub.domain.topico.DatosRespuestaTopico;
import com.aluralatamcursos.forohub.domain.topico.Topico;
import com.aluralatamcursos.forohub.domain.topico.TopicoRepository;
import com.aluralatamcursos.forohub.domain.usuario.Usuario;
import com.aluralatamcursos.forohub.domain.usuario.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OperacionesRespuestas {
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    TopicoRepository topicoRepository;
    @Autowired
    RespuestaRespository respuestaRespository;

    public DatosRespuestaRespuesta crearNuevoRespuesta(DatosRegistroRespuesta datosRegistroRespuesta) {

        Optional<Topico> topico = topicoRepository.findByIdAndStatusNotELIMINADO(datosRegistroRespuesta.idTopico());
        Optional<Usuario> usuario = usuarioRepository.findByIdAndActivoTrue(datosRegistroRespuesta.idUsuario());
        System.out.println("prueba1");
        if (topico.isPresent()){
        }else {
            throw new ValidacionException("No existe el topico con el id que se proporciono");
        }
        if (usuario.isPresent()){
        }else {
            throw new ValidacionException("No existe un usuario con el id proporcionado");
        }
        try {
            System.out.println("prueba2");
            Respuesta respuesta = respuestaRespository.save(new Respuesta(datosRegistroRespuesta,topico.get(),usuario.get()));
            System.out.println("prueba3");
            return new DatosRespuestaRespuesta(respuesta);
        }catch (DataIntegrityViolationException e) {
            throw new ValidacionException(e);
        }
    }

    public Page<DatosListadoRespuesta> consultarListaRespuestas(Pageable paginacion) {
        return respuestaRespository.findAllByStatusNotELIMINADO(paginacion).map(DatosListadoRespuesta::new);
    }

    public DatosRespuestaRespuesta consultarRespuesta(Long id) {
        Optional<Respuesta> respuesta = respuestaRespository.findById(id);
        if (respuesta.isPresent()){
            return new DatosRespuestaRespuesta(respuesta.get());
        }else {
            throw new ValidacionException("No existe una respuesta con el id informado");
        }
    }

    public DatosRespuestaRespuesta actualizarRespuesta(Long id,  DatosActualizarRespuesta datos) {
        Optional<Respuesta> respuesta = respuestaRespository.findByIdAndStatusNotELIMINADO(id);
        if (respuesta.isPresent()){
            try {
                Respuesta respuestaExistente=respuesta.get();
                Optional<Topico> topico=null;
                if (datos.idTopico()!=null){
                    topico = topicoRepository.findByIdAndStatusNotELIMINADO(datos.idTopico());
                    if (!topico.isPresent()){
                        throw new ValidacionException("No existe el topico que se proporciono");
                    }
                }
                respuestaExistente.actualizarDatos(datos,topico);
                return new DatosRespuestaRespuesta(respuestaExistente);
            }catch (DataIntegrityViolationException e) {
                throw new ValidacionException(e);
            }
        }else {
            throw new ValidacionException("No existe el Id del Respuesta que se proporciono");
        }
    }

    public void borrarRespuesta(Long id) {
        var respuesta = respuestaRespository.findById(id);
        if (respuesta.isPresent()){
            var respuestaExiste=respuesta.get();
            respuestaExiste.desactivarRespuesta();
        }
        else{
            throw new ValidacionException("No existe el Id de la respuesta que se proporciono");
        }
    }
}
