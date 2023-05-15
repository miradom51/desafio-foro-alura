package com.forum.alura.forumalura.domain.respuesta.model;

import com.forum.alura.forumalura.domain.Usuario.model.Usuario;
import com.forum.alura.forumalura.domain.respuesta.dto.RespuestaActualizarDTO;
import com.forum.alura.forumalura.domain.respuesta.dto.RespuestaRegistroDTO;
import com.forum.alura.forumalura.domain.topico.models.Topico;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Entity
@Table(name = "respuesta")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Respuesta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "respuesta_id")
    private Long respuestaId;

    private String mensaje;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topico_id")
    private Topico topico;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id")
    private Usuario autor;

    private Boolean solucion = false;




    public Respuesta(RespuestaRegistroDTO registroRespuesta) {
        this.mensaje = registroRespuesta.mensaje();
        this.topico = new Topico(registroRespuesta.topico().getTopicoId());
        this.autor = new Usuario(registroRespuesta.autor().getUsuarioId());
    }


    public void actualizarDatos(RespuestaActualizarDTO actualizarRespuesta) {
        if (actualizarRespuesta.mensaje() != null) {
            this.mensaje = actualizarRespuesta.mensaje();
        }
        if (actualizarRespuesta.solucion() != null) {
            this.solucion = actualizarRespuesta.solucion();
        }
        if (actualizarRespuesta.topico() != null) {
            this.topico = new Topico(actualizarRespuesta.topico().getTopicoId());
        }
        if (actualizarRespuesta.autor() != null) {
            this.autor = new Usuario(actualizarRespuesta.autor().getUsuarioId());
        }
    }
}
