package com.montelongo.FindBook.principal;

import com.montelongo.FindBook.service.ConsumoAPI;

public class Principal {

    private ConsumoAPI consumoAPI = new ConsumoAPI();


    public void mostrarMenu(){
        getObtenerDatos();
    }

    private void getObtenerDatos() {
        System.out.println("Hello world");
    }
}
