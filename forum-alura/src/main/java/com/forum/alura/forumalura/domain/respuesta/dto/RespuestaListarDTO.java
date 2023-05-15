package com.forum.alura.forumalura.domain.respuesta.dto;


import com.forum.alura.forumalura.domain.respuesta.model.Respuesta;

import java.time.LocalDateTime;

public record RespuestaListarDTO(
        Long respuestaId,
        String mensaje,
        LocalDateTime fechaCreacion,
        Boolean solucion) {

    public RespuestaListarDTO(Respuesta respuesta) {
            this(respuesta.getRespuestaId(),
                    respuesta.getMensaje(),
                    respuesta.getFechaCreacion(),
                    respuesta.getSolucion());
        }

}
