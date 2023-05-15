package com.forum.alura.forumalura.controllers;

import com.forum.alura.forumalura.domain.curso.dto.CursoActualizarDTO;
import com.forum.alura.forumalura.domain.curso.dto.CursoListarDTO;
import com.forum.alura.forumalura.domain.curso.dto.CursoRegistroDTO;
import com.forum.alura.forumalura.domain.curso.dto.CursoRespuestaDTO;
import com.forum.alura.forumalura.domain.curso.model.Curso;
import com.forum.alura.forumalura.domain.curso.repository.ICursoRepository;
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
@RequestMapping("cursos")
@AllArgsConstructor
public class CursoController {
   private final ICursoRepository repository;

    @GetMapping
    public ResponseEntity<Page<CursoListarDTO>> listAllCursos(@PageableDefault(size = 10, sort = "cursoId") Pageable page) {
        return ResponseEntity.ok(repository.findAll(page).map(CursoListarDTO::new));
    }


    @GetMapping("/{id}")
    public ResponseEntity<CursoRespuestaDTO> listCurso(@PathVariable Long id) {
        Curso curso = repository.getReferenceById(id);
        var cursos = new CursoRespuestaDTO(curso.getCursoId(),
                curso.getNombre(), curso.getCategoria());

        return ResponseEntity.ok(cursos);
    }

    @PostMapping
    public ResponseEntity<CursoRespuestaDTO> addCurso(@RequestBody @Valid CursoRegistroDTO registroDTO) {
        Curso curso = repository.save(new Curso(registroDTO));
        CursoRespuestaDTO respuestaDTO = new CursoRespuestaDTO(curso.getCursoId(),
                curso.getNombre(), curso.getCategoria());

        return ResponseEntity.ok(respuestaDTO);

    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity updateCurso(@RequestBody @Valid CursoActualizarDTO cursoActualizado) {
        Curso curso = repository.getReferenceById(cursoActualizado.id());
        curso.actualizar(cursoActualizado);
        return ResponseEntity.ok(new CursoActualizarDTO(curso.getCursoId(), curso.getNombre(),
                curso.getCategoria()));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteCurso(@PathVariable Long id) {
        if (repository.existsById(id)) {
            Curso curso = repository.getReferenceById(id);
            repository.delete(curso);
            return ResponseEntity.noContent().build();
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

}



