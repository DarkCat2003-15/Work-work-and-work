package com.example.Estudiantes.controller;

import com.example.Estudiantes.model.Student;
import com.example.Estudiantes.service.StudentService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estudiantes")
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @GetMapping("/carrera/{nombre}")
    public List<Student> listar() {
        return service.listar();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Student guardar(
            @RequestBody Student student) {

        return service.guardar(student);
    }

    @PutMapping("/{id}")
    public Student actualizar(
            @PathVariable Long id,
            @RequestBody Student student) {

        return service.actualizar(id, student);
    }

    @DeleteMapping("/{id}")
    public void eliminar(
            @PathVariable Long id) {

        service.eliminar(id);
    }
}