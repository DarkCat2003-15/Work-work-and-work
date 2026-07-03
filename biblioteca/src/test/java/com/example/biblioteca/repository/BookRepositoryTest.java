package com.example.biblioteca.repository;

import com.example.biblioteca.model.Book;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class BookRepositoryTest {

    @Autowired
    private BookRepository repository;

    @Test
    @DisplayName("Guardar un libro correctamente")
    void guardarLibroTest(){

        // GIVEN
        Book libro = Book.builder()
                .titulo("Java")
                .autor("Juan")
                .categoria("Ciencia")
                .precio(40.0)
                .disponible(true)
                .build();

        // WHEN
        Book resultado = repository.save(libro);

        // THEN
        assertThat(resultado).isNotNull();
        assertThat(resultado.getId()).isGreaterThan(0);

    }

}