package com.forum.alura.forumalura.domain.Usuario.model;

import com.forum.alura.forumalura.domain.Usuario.dto.UsuarioActualizarDTO;
import com.forum.alura.forumalura.domain.Usuario.dto.UsuarioRegistroDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuario")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usuario_id")
    private Long usuarioId;

    private String nombre;
    private String email;
    private String contrasena;

    public Usuario(UsuarioRegistroDTO registroDTO) {
        this.nombre = registroDTO.nombre();
        this.email = registroDTO.email();
        this.contrasena = registroDTO.contrasena();
    }

    public Usuario(Long usuarioId) {
        this.usuarioId = usuarioId;
    }


    public void actualizar(UsuarioActualizarDTO usuarioActualizado) {
        this.nombre = usuarioActualizado.nombre();
        this.email = usuarioActualizado.email();
        this.contrasena = usuarioActualizado.contrasena();
    }


}
