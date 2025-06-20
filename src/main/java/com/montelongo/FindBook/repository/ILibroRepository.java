package com.montelongo.FindBook.repository;

import com.montelongo.FindBook.model.Autor;
import com.montelongo.FindBook.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ILibroRepository extends JpaRepository<Libro, Long> {
     Libro findByTitulo(String titulo);
     List<Libro> findByIdiomasContaining(String idiomas);
}
