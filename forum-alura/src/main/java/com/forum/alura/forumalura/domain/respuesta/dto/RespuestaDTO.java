package com.forum.alura.forumalura.domain.respuesta.dto;

import com.forum.alura.forumalura.domain.Usuario.model.Usuario;
import com.forum.alura.forumalura.domain.topico.models.Topico;

import java.time.LocalDateTime;

public record RespuestaDTO(

        Long respuestaId,
        String mensaje,
        LocalDateTime fechaCreacion,
        Boolean solucion,
        Topico topico,
        Usuario autor) {


}
