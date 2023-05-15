package com.forum.alura.forumalura.domain.topico.repository;

import com.forum.alura.forumalura.domain.topico.models.Topico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITopicoRepository extends JpaRepository<Topico, Long> {
}
