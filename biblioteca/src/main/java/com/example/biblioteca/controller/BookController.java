package com.example.biblioteca.controller;

import com.example.biblioteca.dto.BookRequest;
import com.example.biblioteca.dto.BookResponse;
import com.example.biblioteca.model.Book;
import com.example.biblioteca.service.BookService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/libros")
public class BookController {
    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @GetMapping
    public List<BookResponse> listar() {
        return service.listarTodos().stream()
                .map(this::toResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public BookResponse buscar(@PathVariable Long id) {
        return toResponse(service.buscarPorId(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookResponse guardar(@Valid @RequestBody BookRequest request) {
        Book book = toEntity(request);
        return toResponse(service.guardar(book));
    }

    @PutMapping("/{id}")
    public BookResponse actualizar(@PathVariable Long id, @Valid @RequestBody BookRequest request) {
        Book book = toEntity(request);
        return toResponse(service.actualizar(id, book));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }

    @GetMapping("/categoria/{categoria}")
    public List<BookResponse> categoria(@PathVariable String categoria) {
        return service.buscarCategoria(categoria).stream()
                .map(this::toResponse)
                .toList();
    }

    @GetMapping("/disponibles")
    public List<BookResponse> disponibles() {
        return service.disponibles().stream()
                .map(this::toResponse)
                .toList();
    }

    private BookResponse toResponse(Book book) {
        return BookResponse.builder()
                .id(book.getId())
                .titulo(book.getTitulo())
                .autor(book.getAutor())
                .categoria(book.getCategoria())
                .precio(book.getPrecio())
                .disponible(book.getDisponible())
                .build();
    }

    private Book toEntity(BookRequest request) {
        return Book.builder()
                .titulo(request.getTitulo())
                .autor(request.getAutor())
                .categoria(request.getCategoria())
                .precio(request.getPrecio())
                .disponible(request.getDisponible())
                .build();
    }
}