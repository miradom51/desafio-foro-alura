package com.forum.alura.forumalura.domain.respuesta.dto;

import com.forum.alura.forumalura.domain.Usuario.model.Usuario;
import com.forum.alura.forumalura.domain.topico.models.Topico;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record RespuestaRegistroDTO(
        @NotBlank
        String mensaje,
        @NotNull
        Topico topico,
        @NotNull
        Usuario autor
       ) {



}
