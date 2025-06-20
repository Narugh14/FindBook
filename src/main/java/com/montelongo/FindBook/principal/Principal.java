package com.montelongo.FindBook.principal;


import com.montelongo.FindBook.model.*;
import com.montelongo.FindBook.repository.IAutorRepository;
import com.montelongo.FindBook.repository.ILibroRepository;
import com.montelongo.FindBook.service.ConsumoAPI;
import com.montelongo.FindBook.service.ConvertirDatos;
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
                    //Consulta libros registrados

                    break;
                case 3:
                    //Consultar autores registrados
                    break;
                case 4:
                   //Consultar lista de autores vivos en determinado año
                    break;
                case 5:
                    //Consultar lista de libros por idiomas
                    break;
                case 6:
                    //Consultar Top 10 Mejores libros
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

}
