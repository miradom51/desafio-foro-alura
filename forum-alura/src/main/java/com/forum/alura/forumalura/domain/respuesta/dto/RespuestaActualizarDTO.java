package com.forum.alura.forumalura.domain.respuesta.dto;

import com.forum.alura.forumalura.domain.Usuario.model.Usuario;
import com.forum.alura.forumalura.domain.topico.models.Topico;
import jakarta.validation.constraints.NotNull;




public record RespuestaActualizarDTO(
        @NotNull
        Long id,
        String mensaje,
        Topico topico,
        Usuario autor,
        Boolean solucion) {

        public RespuestaActualizarDTO(@NotNull Long id, String mensaje, Boolean solucion) {
                this(id, mensaje, null, null, solucion);
        }
}
