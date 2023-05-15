package com.forum.alura.forumalura.domain.topico.dto;

import com.forum.alura.forumalura.domain.Usuario.model.Usuario;
import com.forum.alura.forumalura.domain.curso.model.Curso;
import com.forum.alura.forumalura.domain.topico.models.StatusTopico;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TopicoRegistroDTO(
        @NotBlank
        String titulo,
        @NotBlank
        String mensaje,
        @NotNull
        Usuario autor,
        @NotNull
        StatusTopico estado,
        @NotNull
        Curso curso) {

}
