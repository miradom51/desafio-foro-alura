package com.forum.alura.forumalura.domain.topico.models;

import com.forum.alura.forumalura.domain.Usuario.model.Usuario;
import com.forum.alura.forumalura.domain.curso.model.Curso;
import com.forum.alura.forumalura.domain.respuesta.model.Respuesta;
import com.forum.alura.forumalura.domain.topico.dto.TopicoActualizarDTO;
import com.forum.alura.forumalura.domain.topico.dto.TopicoRegistroDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "topico")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Topico {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "topico_id")
    private Long topicoId;

    @NotBlank
    private String titulo;

    @NotBlank
    private String mensaje;

    @CreatedDate
    @NotNull
    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    @NotNull
    @Enumerated(EnumType.STRING)
    private StatusTopico estado = StatusTopico.NO_RESPONDIDO;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario autor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso_id")
    private Curso curso;

    @NotNull
    @OneToMany(mappedBy = "topico")
    private List<Respuesta> respuestas = new ArrayList<>();

    private boolean activo;

    public Topico(Long topicoId) {
        this.topicoId = topicoId;
    }

    public Topico(TopicoRegistroDTO registroTopico) {
        this.titulo = registroTopico.titulo();
        this.mensaje = registroTopico.mensaje();
        this.estado = registroTopico.estado();
        this.autor = registroTopico.autor();
        this.curso = registroTopico.curso();
    }


    public void desactivarTopico() {
        this.activo=false;
    }

    public void actualizarDTO(TopicoActualizarDTO updateTopico) {
        if (updateTopico.titulo() != null) {
            this.titulo = updateTopico.titulo();
        }
        if (updateTopico.mensaje() != null) {
            this.mensaje = updateTopico.mensaje();
        }
        if (updateTopico.estado() != null) {
            this.estado = updateTopico.estado();
        }
        if (updateTopico.autor() != null) {
            this.autor = new Usuario(updateTopico.autor().getUsuarioId());
        }
        if (updateTopico.curso() != null) {
            this.curso = new Curso(updateTopico.curso().getCursoId());
        }
    }




}
