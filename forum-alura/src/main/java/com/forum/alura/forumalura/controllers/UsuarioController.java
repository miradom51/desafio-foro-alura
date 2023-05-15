package com.forum.alura.forumalura.controllers;

import com.forum.alura.forumalura.domain.Usuario.dto.UsuarioActualizarDTO;
import com.forum.alura.forumalura.domain.Usuario.dto.UsuarioListarDTO;
import com.forum.alura.forumalura.domain.Usuario.dto.UsuarioRegistroDTO;
import com.forum.alura.forumalura.domain.Usuario.dto.UsuarioRespuestaDTO;
import com.forum.alura.forumalura.domain.Usuario.model.Usuario;
import com.forum.alura.forumalura.domain.Usuario.repository.IUsuarioRepository;
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
@RequestMapping("/usuarios")
@AllArgsConstructor
public class UsuarioController {
    private final IUsuarioRepository repository;


    @GetMapping
    public ResponseEntity<Page<UsuarioListarDTO>> listAllUsuarios(@PageableDefault(size = 10, sort = "usuarioId") Pageable page) {
        return ResponseEntity.ok(repository.findAll(page).map(UsuarioListarDTO::new));
    }


    @GetMapping("/{id}")
    public ResponseEntity<UsuarioRespuestaDTO> listUsuario(@PathVariable Long id) {
        Usuario usuario = repository.getReferenceById(id);
        var usuarios = new UsuarioRespuestaDTO(usuario.getUsuarioId(),
                usuario.getNombre(), usuario.getEmail());

        return ResponseEntity.ok(usuarios);
    }

    @PostMapping
    public ResponseEntity<UsuarioRespuestaDTO> registrarMedico(@RequestBody @Valid UsuarioRegistroDTO registroDTO) {
        Usuario usuario = repository.save(new Usuario(registroDTO));
        UsuarioRespuestaDTO respuestaDTO = new UsuarioRespuestaDTO(usuario.getUsuarioId(),
                usuario.getNombre(), usuario.getEmail());

        return ResponseEntity.ok((respuestaDTO));

    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity actualizarcurso(@RequestBody @Valid UsuarioActualizarDTO usuarioActualizado) {
        Usuario usuario = repository.getReferenceById(usuarioActualizado.id());
        usuario.actualizar(usuarioActualizado);
        return ResponseEntity.ok(new UsuarioActualizarDTO(usuario.getUsuarioId(), usuario.getNombre(),
                usuario.getEmail(), usuario.getContrasena()));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarCurso(@PathVariable Long id) {
        if (repository.existsById(id)) {
            Usuario usuario = repository.getReferenceById(id);
            repository.delete(usuario);
            return ResponseEntity.noContent().build();
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }


}