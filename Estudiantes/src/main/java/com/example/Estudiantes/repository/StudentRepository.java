package com.example.Estudiantes.repository;

import com.example.Estudiantes.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository
        extends JpaRepository<Student, Long> {

}