package com.montelongo.FindBook.principal;

import com.montelongo.FindBook.model.DatosLibro;
import com.montelongo.FindBook.model.DatosLibros;
import com.montelongo.FindBook.service.ConsumoAPI;
import com.montelongo.FindBook.service.ConvertirDatos;

import java.util.Scanner;

public class Principal {

    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConvertirDatos convierte = new ConvertirDatos();

    private final String URL_BASE = "http://gutendex.com/books/?ids=1,2";

    //Esta API de consulta no necesita api key
    //private final String API_KEY = "";


    public void mostrarMenu(){
        var json = consumoAPI.obtenerDatos(URL_BASE);
        DatosLibros libro = convierte.obtenerDatos(json, DatosLibros.class);
        System.out.println(libro);
    }

    private void getObtenerDatos() {
        System.out.println("Hello world");
    }
}
