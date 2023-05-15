package com.forum.alura.forumalura.domain.Usuario.repository;

import com.forum.alura.forumalura.domain.Usuario.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {
}
