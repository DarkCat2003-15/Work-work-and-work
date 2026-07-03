package com.example.Estudiantes.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private  String correo;
    private String carrera;
    private Integer edad;


    public Student(){
    }

    public Student(String nombre, String correo, String carrera, Integer edad){
        this.nombre=nombre;
        this.correo=correo;
        this.carrera=carrera;
        this.edad=edad;
    }

    public Long getId() {
        return id;
    }
    public String getNombre(){
        return nombre;
    }
    public void setNombre(String nombre){
        this.nombre=nombre;
    }

    public String getCorreo(){
        return correo;
    }

    public void  setCorreo(String correo){
        this.correo=correo;
    }

    public String getCarrera(){
        return carrera;
    }

    public void setCarrera(String carrera){
        this.carrera=carrera;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }
}
