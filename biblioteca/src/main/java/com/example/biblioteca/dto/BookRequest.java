package com.example.biblioteca.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class BookRequest {

    @NotBlank(message = "El título es obligatorio")
    private String titulo;

    @NotBlank(message = "El autor es obligatorio")
    private String autor;

    @NotBlank(message = "La categoría es obligatoria")
    private String categoria;

    @Positive(message = "El precio debe ser positivo")
    private Double precio;

    private Boolean disponible;
}
