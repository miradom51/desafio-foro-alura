package com.forum.alura.forumalura.domain.topico.dto;


import com.forum.alura.forumalura.domain.Usuario.model.Usuario;
import com.forum.alura.forumalura.domain.curso.model.Curso;
import com.forum.alura.forumalura.domain.topico.models.StatusTopico;

import java.time.LocalDateTime;

public record TopicoRespuestaDTO(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        StatusTopico estado,
        Usuario autor,
        Curso curso) {


}
