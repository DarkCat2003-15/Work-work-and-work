package com.example.biblioteca.service;

import com.example.biblioteca.exception.ResourceNotFoundException;
import com.example.biblioteca.model.Book;
import com.example.biblioteca.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository repository;

    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public List<Book> listarTodos() {
        return repository.findAll();
    }

    public Book buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Libro no encontrado con id: " + id));
    }

    public Book guardar(Book book) {
        return repository.save(book);
    }

    public Book actualizar(Long id, Book nuevoLibro) {
        Book libro = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Libro no encontrado con id: " + id));

        libro.setTitulo(nuevoLibro.getTitulo());
        libro.setAutor(nuevoLibro.getAutor());
        libro.setCategoria(nuevoLibro.getCategoria());
        libro.setPrecio(nuevoLibro.getPrecio());
        libro.setDisponible(nuevoLibro.getDisponible());

        return repository.save(libro);
    }

    public void eliminar(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Libro no encontrado con id: " + id);
        }
        repository.deleteById(id);
    }

    public List<Book> buscarCategoria(String categoria) {
        return repository.findByCategoria(categoria);
    }

    public List<Book> disponibles() {
        return repository.findByDisponible(true);
    }

}