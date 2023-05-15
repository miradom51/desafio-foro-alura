package com.forum.alura.forumalura.controllers;

import com.forum.alura.forumalura.domain.Usuario.model.Usuario;
import com.forum.alura.forumalura.domain.respuesta.dto.RespuestaActualizarDTO;
import com.forum.alura.forumalura.domain.respuesta.dto.RespuestaDTO;
import com.forum.alura.forumalura.domain.respuesta.dto.RespuestaListarDTO;
import com.forum.alura.forumalura.domain.respuesta.dto.RespuestaRegistroDTO;
import com.forum.alura.forumalura.domain.respuesta.model.Respuesta;
import com.forum.alura.forumalura.domain.respuesta.repository.IRespuestaRepository;
import com.forum.alura.forumalura.domain.topico.models.Topico;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/respuestas")
@AllArgsConstructor
public class RespuestaController {
    private final IRespuestaRepository repository;


    @GetMapping
    public ResponseEntity<Page<RespuestaListarDTO>> listAllRespuesta(@PageableDefault(size = 10, sort = "respuestaId") Pageable page) {
        return ResponseEntity.ok(repository.findAll(page).map(RespuestaListarDTO::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RespuestaDTO> listRespuesta(@PathVariable Long id) {
        Respuesta respuesta = repository.getReferenceById(id);
        var respuestas = new RespuestaDTO(respuesta.getRespuestaId(),
                respuesta.getMensaje(), respuesta.getFechaCreacion(),
                respuesta.getSolucion(),
                new Topico(respuesta.getTopico().getTopicoId()),
                new Usuario(respuesta.getAutor().getUsuarioId()));

        return ResponseEntity.ok(respuestas);
    }


    @PostMapping
    public ResponseEntity<RespuestaDTO> addCurso(@RequestBody @Valid RespuestaRegistroDTO registroDTO) {
        Respuesta respuesta = repository.save(new Respuesta(registroDTO));
        RespuestaDTO respuestaDTO = new RespuestaDTO(respuesta.getRespuestaId(),
                respuesta.getMensaje(), respuesta.getFechaCreacion(),
                respuesta.getSolucion(), respuesta.getTopico(),respuesta.getAutor());

        return ResponseEntity.ok(respuestaDTO);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity actualizarDatos(@RequestBody @Valid RespuestaActualizarDTO actualizarRespuesta) {
        if (repository.existsById(actualizarRespuesta.id())) {
            Respuesta respuesta = repository.getReferenceById(actualizarRespuesta.id());
            respuesta.actualizarDatos(actualizarRespuesta);
            return ResponseEntity.ok(new RespuestaDTO(
                    respuesta.getRespuestaId(),
                    respuesta.getMensaje(),
                    respuesta.getFechaCreacion(),
                    respuesta.getSolucion(),
                    new Topico(respuesta.getTopico().getTopicoId()),
                    new Usuario(respuesta.getAutor().getUsuarioId()))
            );
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteRespuesta(@PathVariable Long id) {
        if (repository.existsById(id)) {
            Respuesta respuesta = repository.getReferenceById(id);
            repository.delete(respuesta);
            return ResponseEntity.noContent().build();
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }


}