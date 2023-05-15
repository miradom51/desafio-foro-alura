package com.forum.alura.forumalura.domain.curso.model;

import com.forum.alura.forumalura.domain.curso.dto.CursoActualizarDTO;
import com.forum.alura.forumalura.domain.curso.dto.CursoRegistroDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Table(name = "curso")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Curso {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "curso_id")
    private Long cursoId;

    private String nombre;
    private String categoria;


    public Curso(Long cursoId) {
        this.cursoId = cursoId;
    }


    public Curso(CursoRegistroDTO registroDTO) {
        this.nombre = registroDTO.nombre();
        this.categoria = registroDTO.categoria();
    }


    public void actualizar(CursoActualizarDTO cursoActualizado) {
        this.nombre = cursoActualizado.nombre();
        this.categoria = cursoActualizado.categoria();
    }





}


