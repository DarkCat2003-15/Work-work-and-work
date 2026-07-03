package com.example.Estudiantes.service;

import com.example.Estudiantes.model.Student;
import com.example.Estudiantes.repository.StudentRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public List<Student> listar() {
        return repository.findAll();
    }

    public Student guardar(Student student) {
        return repository.save(student);
    }

    public Student actualizar(Long id,
                              Student student) {

        Student existente =
                repository.findById(id)
                        .orElseThrow();

        existente.setNombre(student.getNombre());
        existente.setCorreo(student.getCorreo());
        existente.setCarrera(student.getCarrera());
        existente.setEdad(student.getEdad());

        return repository.save(existente);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}