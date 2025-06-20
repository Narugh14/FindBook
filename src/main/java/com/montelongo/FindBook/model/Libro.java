package com.montelongo.FindBook.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "libros")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;

    @ManyToOne
    @JoinColumn(name ="autor_id", nullable = false)
    private Autor autor;

    @Column(name = "nombre_autor")
    private String nombreAutor;

    @Column(name = "idiomas")
    private String idiomas;
    private double numeroDescargas;

    public Libro(DatosLibro datosLibro, Autor autor) {
        this.titulo = datosLibro.title();
        setIdiomas(datosLibro.idiomas());
        this.numeroDescargas = datosLibro.numDescarga();
        this.nombreAutor = autor.getName();
        this.autor = autor;
    }

    private void setIdiomas(List<String> idiomas) {
        this.idiomas = String.join(",", idiomas);
    }
}
