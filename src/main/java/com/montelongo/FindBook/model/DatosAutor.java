package com.montelongo.FindBook.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosAutor(
        @JsonAlias("name")  String name,
        @JsonAlias("birth_year") int FechaNacimiento,
        @JsonAlias("death_year") int FechaMuerte
) {
}
