package com.montelongo.FindBook.service;

public interface IConvertirDatos {
    <T> T obtenerDatos(String json, Class<T> clase);
}
