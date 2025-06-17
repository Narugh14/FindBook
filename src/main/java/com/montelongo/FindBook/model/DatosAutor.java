package com.montelongo.FindBook.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosAutor(
        @JsonAlias("name")  String Autor,
        @JsonAlias("birth_year") Integer FechaNacimiento,
        @JsonAlias("death_year") Integer FechaMuerte
) {
}
