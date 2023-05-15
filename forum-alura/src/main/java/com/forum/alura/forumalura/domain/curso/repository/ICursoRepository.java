package com.forum.alura.forumalura.domain.curso.repository;

import com.forum.alura.forumalura.domain.curso.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ICursoRepository extends JpaRepository<Curso, Long> {

}
