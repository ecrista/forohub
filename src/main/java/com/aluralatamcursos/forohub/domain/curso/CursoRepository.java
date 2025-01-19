package com.aluralatamcursos.forohub.domain.curso;

import jakarta.validation.constraints.NotNull;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CursoRepository extends JpaRepository<Curso,Long> {
    Optional<Curso> findByNombre(String curso);
}
