package com.example.biblioteca.services;

import com.example.biblioteca.model.Book;
import com.example.biblioteca.repository.BookRepository;
import com.example.biblioteca.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private BookRepository repository;

    @InjectMocks
    private BookService service;

    private Book libro;

    @BeforeEach
    void setUp() {

        libro = Book.builder()
                .titulo("Java")
                .autor("Juan")
                .categoria("Ciencia")
                .precio(40.0)
                .disponible(true)
                .build();
    }

    @Test
    @DisplayName("Guardar libro usando Mockito")
    void guardarLibro() {

        given(repository.save(any(Book.class))).willReturn(libro);

        Book resultado = service.guardar(libro);

        assertThat(resultado).isNotNull();
        assertThat(resultado.getTitulo()).isEqualTo("Java");
    }
}