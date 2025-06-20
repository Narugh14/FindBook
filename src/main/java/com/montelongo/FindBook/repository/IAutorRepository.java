package com.montelongo.FindBook.repository;

import com.montelongo.FindBook.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IAutorRepository extends JpaRepository<Autor, Long> {
    Autor findByNameIgnoreCase(String nombre);
    List<Autor> findByFechaNacimientoLessThanEqualAndFechaMuerteGreaterThanEqual(int fechaInicio, int fechaFinal);
}
