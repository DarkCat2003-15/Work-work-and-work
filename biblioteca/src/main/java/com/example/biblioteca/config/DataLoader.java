package com.example.biblioteca.config;

import com.example.biblioteca.model.Book;
import com.example.biblioteca.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final BookRepository repository;

    public DataLoader(BookRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) {
        if (repository.count() > 0) {
            return;
        }

        repository.save(Book.builder()
                .titulo("Clean Code")
                .autor("Robert Martin")
                .categoria("Programación")
                .precio(45.50)
                .disponible(true)
                .build());

        repository.save(Book.builder()
                .titulo("Spring in Action")
                .autor("Craig Walls")
                .categoria("Programación")
                .precio(60.00)
                .disponible(true)
                .build());

        repository.save(Book.builder()
                .titulo("El Principito")
                .autor("Antoine de Saint-Exupéry")
                .categoria("Novela")
                .precio(18.50)
                .disponible(false)
                .build());
    }
}