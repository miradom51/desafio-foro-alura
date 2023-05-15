package com.forum.alura.forumalura.domain.topico.dto;


import com.forum.alura.forumalura.domain.Usuario.model.Usuario;
import com.forum.alura.forumalura.domain.curso.model.Curso;
import com.forum.alura.forumalura.domain.topico.models.StatusTopico;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TopicoActualizarDTO(
        @NotNull
        Long id,
        @NotBlank
        String titulo,
        @NotBlank
        String mensaje,
        @NotNull
        StatusTopico estado,
        @NotNull
        Usuario autor,
        @NotNull
        Curso curso
) {
}
