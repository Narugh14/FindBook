package com.montelongo.FindBook.principal;


import com.montelongo.FindBook.model.*;
import com.montelongo.FindBook.repository.IAutorRepository;
import com.montelongo.FindBook.repository.ILibroRepository;
import com.montelongo.FindBook.service.ConsumoAPI;
import com.montelongo.FindBook.service.ConvertirDatos;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Principal {

    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConvertirDatos convierte = new ConvertirDatos();
    private ILibroRepository libroRepository;
    private IAutorRepository autorRepository;

    private final String URL_BASE = "http://gutendex.com/books/";

    public Principal(IAutorRepository autorRepository, ILibroRepository libroRepository) {
        this.autorRepository = autorRepository;
        this.libroRepository = libroRepository;
    }

    //Metodo que lo unico que hace es mostrar e interacturar con el usuario
    //por medio de la terminar
    public void mostrarMenu(){

        var opcion = -1;
        System.out.println("Bienvenido! Por favor selecciona una opción: ");
        //Menu interactivo con el usuario final
        while(opcion != 0){
            var menu = """
                    
                    1 - Buscar libro por titulo
                    2 - Listar libros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos en un determinado año
                    5 - Listar libros por idiomas
                    6 - Top 10 mejores libros
                    
                    0 - Salir
                    """;
            System.out.println(menu);
            while (!teclado.hasNextInt()){
                System.out.println("Error, por favor ingrese un numero valido.");
                teclado.nextLine();
            }
            opcion = teclado.nextInt();
            teclado.nextLine();
            //Casos de opcines seleccionada por el usuario
            switch (opcion){
                case 1:
                    //Consultar libro
                    buscarLibroPorTitulo();
                    break;
                case 2:
                    listarLibrosRegistrados();
                    break;
                case 3:
                    listarAutoresRegistrados();
                    break;
                case 4:
                    listarAutoresPorAño();
                    break;
                case 5:
                    listarLibrosPorIdiomas();
                    break;
                case 6:
                    top10MejoresLibros();
                    break;
                case 0:
                    System.out.println("Cerrando aplicación");
                    break;
                default:
                    System.out.println("Opcion valida");
            }
        }
    }

    private void buscarLibroPorTitulo() {
        System.out.println("Ingrese el titulo que desea buscar mayor informacion:");
        Datos datos = getDatosLibro(teclado.nextLine().trim());
        if(!datos.result().isEmpty()){
            DatosLibro datosLibro = datos.result().get(0);
            DatosAutor datosAutor = datosLibro.authors().get(0);
            Libro libro = null;
            Libro libroRepositorio = libroRepository.findByTitulo(datosLibro.title());
            if(libroRepositorio != null){
                System.out.println("Libro en base de datos");
                System.out.println(libroRepositorio.toString());
            }else{
                Autor autorRepositorio = autorRepository.findByNameIgnoreCase(datosLibro.authors().get(0).name());
                if(autorRepositorio != null){
                    libro = crearLibro(datosLibro,autorRepositorio);
                    libroRepository.save(libro);
                    System.out.println("Libro agregado a la Base datos");
                    System.out.println(libro.toString());
                }else{
                    Autor autor = new Autor(datosAutor);
                    autor = autorRepository.save(autor);
                    libro = crearLibro(datosLibro,autor);
                    libroRepository.save(libro);
                    System.out.println("Libro agregado");
                    System.out.println(libro);
                }
            }
        }else{
            System.out.println("El libro no existe en la API de Gutendex, ingrese otro");
        }
    }

    private Datos getDatosLibro(String tituloLibro){
        tituloLibro.replace(" ", "%20");
        var json = consumoAPI.obtenerDatos(URL_BASE + "?search="+tituloLibro);
        Datos datosLibros = convierte.obtenerDatos(json, Datos.class);
        return datosLibros;
  }

  private Libro crearLibro(DatosLibro datosLibro, Autor autor){
        if(autor != null){
            return new Libro(datosLibro,autor);
        }else{
            System.out.println("El autor es null, no se puede crear el libro");
            return null;
        }
  }
    private void listarLibrosRegistrados() {
        List<Libro> libros = libroRepository.findAll();
        if (libros.isEmpty()) {
            System.out.println("No hay libros registrados");
            return;
        }
        System.out.println("----- LOS LIBROS REGISTRADOS SON: -----\n");
        libros.stream()
                .sorted(Comparator.comparing(Libro::getTitulo))
                .forEach(System.out::println);
    }
    private void listarAutoresRegistrados() {
        List<Autor> autores = autorRepository.findAll();
        if (autores.isEmpty()) {
            System.out.println("No hay autores registrados");
            return;
        }
        System.out.println("----- LOS AUTORES REGISTRADOS SON: -----\n");
        autores.stream()
                .sorted(Comparator.comparing(Autor::getName))
                .forEach(System.out::println);
    }
    private void listarAutoresPorAño() {
        System.out.println("Escribe el año en el que deseas buscar: ");
        var año = teclado.nextInt();
        teclado.nextLine();
        if(año < 0) {
            System.out.println("El año no puede ser negativo, intenta de nuevo");
            return;
        }
        List<Autor> autoresPorAño = autorRepository.findByFechaNacimientoLessThanEqualAndFechaMuerteGreaterThanEqual(año, año);
        if (autoresPorAño.isEmpty()) {
            System.out.println("No hay autores registrados en ese año");
            return;
        }
        System.out.println("----- LOS AUTORES VIVOS REGISTRADOS EN EL AÑO " + año + " SON: -----\n");
        autoresPorAño.stream()
                .sorted(Comparator.comparing(Autor::getName))
                .forEach(System.out::println);
    }
    private void listarLibrosPorIdiomas() {
        System.out.println("Escribe el idioma por el que deseas buscar: ");
        String menu = """
                es - Español
                en - Inglés
                fr - Francés
                pt - Portugués
                """;
        System.out.println(menu);
        var idioma = teclado.nextLine();
        if (!idioma.equals("es") && !idioma.equals("en") && !idioma.equals("fr") && !idioma.equals("pt")) {
            System.out.println("Idioma no válido, intenta de nuevo");
            return;
        }
        List<Libro> librosPorIdioma = libroRepository.findByIdiomasContaining(idioma);
        if (librosPorIdioma.isEmpty()) {
            System.out.println("No hay libros registrados en ese idioma");
            return;
        }
        System.out.println("----- LOS LIBROS REGISTRADOS EN EL IDIOMA SELECCIONADO SON: -----\n");
        librosPorIdioma.stream()
                .sorted(Comparator.comparing(Libro::getTitulo))
                .forEach(System.out::println);
    }
    private void top10MejoresLibros() {
        System.out.println("De donde quieres obtener los libros más descargados?");
        String menu = """
                1 - Gutendex
                2 - Base de datos
                """;
        System.out.println(menu);
        var opcion = teclado.nextInt();
        teclado.nextLine();

        if (opcion == 1) {
            System.out.println("----- LOS 10 LIBROS MÁS DESCARGADOS EN GUTENDEX SON: -----\n");
            var json = consumoAPI.obtenerDatos(URL_BASE);
            Datos datos = convierte.obtenerDatos(json, Datos.class);
            List<Libro> libros = new ArrayList<>();
            for (DatosLibro datosLibros : datos.result()) {

                if (!datosLibros.authors().isEmpty()) {
                    Autor autor = new Autor(datosLibros.authors().get(0));
                    Libro libro = new Libro(datosLibros, autor);
                    libros.add(libro);
                } else {
                    System.out.println("No se encontró autor para el libro: " + datosLibros.title());
                }
            }
            libros.stream()
                    .sorted(Comparator.comparing(Libro::getNumeroDescargas).reversed())
                    .limit(10)
                    .forEach(System.out::println);
        } else if (opcion == 2) {
            System.out.println("----- LOS 10 LIBROS MÁS DESCARGADOS EN LA BASE DE DATOS SON: -----\n");
            List<Libro> libros = libroRepository.findAll();
            if (libros.isEmpty()) {
                System.out.println("No hay libros registrados");
                return;
            }
            libros.stream()
                    .sorted(Comparator.comparing(Libro::getNumeroDescargas).reversed())
                    .limit(10)
                    .forEach(System.out::println);
        } else {
            System.out.println("Opción no válida, intenta de nuevo");
        }

    }
}
