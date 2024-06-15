package com.alura.litelalura.Principal;

import com.alura.litelalura.Model.Datos;
import com.alura.litelalura.Model.DatosLibro;
import com.alura.litelalura.Model.Libro;
import com.alura.litelalura.Repository.LibroRepositorio;
import com.alura.litelalura.Service.ConsumoApi;
import com.alura.litelalura.Service.ConvierteDatos;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {
    private ConsumoApi consumoApi = new ConsumoApi();
    private ConvierteDatos conversor = new ConvierteDatos();
    private Scanner teclado = new Scanner(System.in);
    private static final String URL_KEY = "https://gutendex.com/books/";
    private LibroRepositorio repositorio;
    private List<Libro> listBusquedaLibro;
    Optional<Libro> buscarLibro;


    public Principal(LibroRepositorio repository) {
        this.repositorio = repository;
    }


    public void mostrarMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    ___***MENU DE OPCIONES LITERALURA***___

                    1 - Buscar libro por titulo
                    2 - Listar libros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos en un determinado año
                    5 - Listar libros por idioma
                    0 - Salir

                    Ingrese una opción:
                    """;
            System.out.print(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();
            System.out.println();

            switch (opcion) {
                case 1:
                    buscarLibroWeb();
                    break;
                case 2:
                    librosRegistrados();
                    break;
                case 3:
                    autoresRegistrados();
                    break;
                case 4:
                    autoresVivos();
                    break;
                case 5:
                    idiomasLibros();
                    break;
                case 0:
                    System.out.println("\n|---------------------------|");
                    System.out.println("|                           |");
                    System.out.println("|       ¡Hasta pronto!      |");
                    System.out.println("|                           |");
                    System.out.println("|---------------------------|");
                    break;
                default:
                    System.out.print("Ingrese una opción valida.\n\n");
            }
        }

    }

    private DatosLibro getDatosLibro() {
        System.out.print("Escribe el nombre del libro a buscar: ");
        String libroBusqueda = teclado.nextLine();
        buscarLibro = repositorio.findFirstByTituloContainsIgnoreCase(libroBusqueda);

        if (buscarLibro.isPresent()) {
            System.out.println("El libro ya existe en la base de datos.\n");
            return null;
        } else {
            var json = consumoApi.obtenerDatos(URL_KEY + "?search=" + libroBusqueda.replace(" ", "+"));
            var datosBusqueda = conversor.obtenerDatos(json, Datos.class);

            Optional<DatosLibro> libroBuscado = datosBusqueda.resultado().stream()
                    .filter(l -> l.titulo().toUpperCase().contains(libroBusqueda.toUpperCase()))
                    .findFirst();

            if (libroBuscado.isPresent()) {
                System.out.println(libroBuscado.get());
                return libroBuscado.get();
            } else {
                System.out.println("No se ha podido encontrar el título del libro...\n\n");
            }
        }
        return null;
    }

    private void buscarLibroWeb() {
        DatosLibro datos = getDatosLibro();
        if (datos != null){
            Libro libro = new Libro(datos);
            repositorio.save(libro);
            System.out.println("\n¡Libro encontrado con éxito!");
            System.out.println("Titulo: " + libro.getTitulo());
            System.out.println("Autor: " + libro.getAutor());
            System.out.println("Descargas: " + libro.getEvaluacion());
            System.out.println("Idioma: " + libro.getIdioma());
            System.out.println();
        }
    }

    private void librosRegistrados() {
        System.out.println("--**Lista de libros registrados**--\n");
        listBusquedaLibro = repositorio.findAll();
        listBusquedaLibro.stream()
                .forEach(libro -> {
                    System.out.println("ID: " + libro.getId());
                    System.out.println("Título: " + libro.getTitulo());
                    System.out.println("Autor: " + libro.getAutor());
                    System.out.println("Fecha de Nacimiento del Autor: " + libro.getFechaDeNacimiento());
                    System.out.println("Evaluación: " + libro.getEvaluacion());
                    System.out.println("Idioma: " + libro.getIdioma());
                    System.out.println("--------------------------------");
                    System.out.println(); // Separador entre libros
                });
    }

    private void autoresRegistrados() {
        listBusquedaLibro = repositorio.buscarAutorRegistrado();
        listBusquedaLibro.stream().forEach(libro -> {
            System.out.println("Autor: " + libro.getAutor());
            System.out.println("Título: " + libro.getTitulo());
            System.out.println("--------------------------------");
            System.out.println(); // Separador entre libros
        });
    }

    private void autoresVivos() {
        System.out.print("Que el año para buscar los autores: ");
        var buscarAutores = teclado.nextLine();
        listBusquedaLibro = repositorio.buscarAutores(buscarAutores);
        listBusquedaLibro.stream().forEach(libro -> {
            if (libro != null){
                System.out.println("Autor: " + libro.getAutor());
                System.out.println();
                System.out.println("--------------------------------");
                System.out.println(); // Separador entre libros
            }
        });
        if (listBusquedaLibro.isEmpty()){
            System.out.println("No se han encontrado autores para esta determinada fecha '" + buscarAutores + "'\n");
        }
    }

    private void idiomasLibros() {
        System.out.print("Que idioma desea buscar: ");
        var buscarIdioma = teclado.nextLine();
        listBusquedaLibro = repositorio.buscarLibroPorIdioma(buscarIdioma);
        listBusquedaLibro.stream().forEach(libro -> {
            System.out.println("Idioma: " + libro.getIdioma());
            System.out.println("Título: " + libro.getTitulo());
            System.out.println();
            System.out.println("--------------------------------");
            System.out.println(); // Separador entre libros
        });
        if (listBusquedaLibro.isEmpty()){
            System.out.println("No se han encontrado libros para este idioma '"+ buscarIdioma + "'\n");
        }
    }


}

