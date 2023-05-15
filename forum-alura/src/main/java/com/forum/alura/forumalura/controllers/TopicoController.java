package com.forum.alura.forumalura.controllers;

import com.forum.alura.forumalura.domain.Usuario.model.Usuario;
import com.forum.alura.forumalura.domain.curso.model.Curso;
import com.forum.alura.forumalura.domain.topico.repository.ITopicoRepository;
import com.forum.alura.forumalura.domain.topico.dto.TopicoActualizarDTO;
import com.forum.alura.forumalura.domain.topico.dto.TopicoListarDTO;
import com.forum.alura.forumalura.domain.topico.dto.TopicoRegistroDTO;
import com.forum.alura.forumalura.domain.topico.dto.TopicoRespuestaDTO;
import com.forum.alura.forumalura.domain.topico.models.Topico;
import jakarta.validation.Valid;
import jdk.jshell.execution.Util;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("topicos")
@AllArgsConstructor
public class TopicoController {
    public final ITopicoRepository repository;

    @GetMapping
    public ResponseEntity<Page<TopicoListarDTO>> listadoTopico(@PageableDefault(size = 10, sort = "topicoId") Pageable page) {
        return ResponseEntity.ok(repository.findAll(page).map(TopicoListarDTO::new));
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> retornaDatosTopico(@PathVariable Long id) {
        if (repository.existsById(id)) {
            Topico topico = repository.getReferenceById(id);
            var datosTopico = new TopicoListarDTO( topico.getTopicoId(),
                    topico.getTitulo(),
                    topico.getMensaje(),
                    topico.getFechaCreacion().toString());
            return ResponseEntity.ok(datosTopico);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }




    @PostMapping
    public ResponseEntity<TopicoRespuestaDTO> registrarTopico(@RequestBody @Valid TopicoRegistroDTO datosRegistroTopico,
                                                                UriComponentsBuilder uriComponentsBuilder) {
        Topico topico = repository.save(new Topico(datosRegistroTopico));
        TopicoRespuestaDTO datosRespuestaTopico = new TopicoRespuestaDTO(
                topico.getTopicoId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.getEstado(),
                new Usuario(topico.getAutor().getUsuarioId()),
                new Curso(topico.getCurso().getCursoId()));

        URI url = uriComponentsBuilder.path("/topico/{id}").buildAndExpand(topico.getTopicoId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaTopico);
    }

    @PutMapping()
    @Transactional
    public ResponseEntity ActualizarTopico(@RequestBody @Valid TopicoActualizarDTO updateTopico) {
        if (repository.existsById(updateTopico.id())) {
            Topico topico = repository.getReferenceById(updateTopico.id());
            topico.actualizarDTO(updateTopico);
            return ResponseEntity.ok(new TopicoRespuestaDTO(
                    topico.getTopicoId(),
                    topico.getTitulo(),
                    topico.getMensaje(),
                    topico.getFechaCreacion(),
                    topico.getEstado(),
                    new Usuario(topico.getAutor().getUsuarioId()),
                    new Curso(topico.getCurso().getCursoId())));
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }



    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarTopico(@PathVariable Long id){
        Topico topico = repository.getReferenceById(id);
        topico.desactivarTopico();
        return ResponseEntity.noContent().build();
    }

}
