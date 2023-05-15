package com.forum.alura.forumalura.domain.respuesta.repository;

import com.forum.alura.forumalura.domain.respuesta.model.Respuesta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRespuestaRepository extends JpaRepository<Respuesta, Long> {
}
