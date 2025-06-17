package com.montelongo.FindBook.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibro (
        @JsonAlias("id") Integer id,
        @JsonAlias("title") String title,
        @JsonAlias("copyright") boolean copyright,
        @JsonAlias("Authors") List<DatosAutor> authors

){
}
