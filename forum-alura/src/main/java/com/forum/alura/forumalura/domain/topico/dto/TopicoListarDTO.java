package com.forum.alura.forumalura.domain.topico.dto;

import com.forum.alura.forumalura.domain.topico.models.Topico;

public record TopicoListarDTO(

        Long id,
        String titulo,
        String mensaje,
        String fechaCreacion) {

    public TopicoListarDTO(Topico topico) {
        this(topico.getTopicoId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion().toString());
    }

}
