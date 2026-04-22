package com.example.tp3moviles.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tp3moviles.model.Libro;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BookViewModel extends ViewModel {

    private final List<Libro> libros;
    private final MutableLiveData<Libro> libroEncontrado = new MutableLiveData<>();
    private final MutableLiveData<String> mensajeError = new MutableLiveData<>();

    public BookViewModel() {
        libros = new ArrayList<>();
        cargarLibros();
    }

    private void cargarLibros() {
        libros.add(new Libro("El Hobbit", "J.R.R. Tolkien", 1937, 300,
                Arrays.asList("Fantasía", "Aventura"),
                "El Hobbit, o ida y vuelta es una novela de fantasía para niños del autor inglés J. R. R. Tolkien. Fue publicado en 1937 con gran éxito de crítica, siendo nominado a la Medalla Carnegie y galardonado con un premio del New York Herald Tribune a la mejor ficción juvenil."));
        libros.add(new Libro("Cien años de soledad", "Gabriel García Márquez", 1967, 417,
                Arrays.asList("Ficción", "Realismo mágico"),
                "Novela que cuenta la historia de la familia Buendía a lo largo de siete generaciones en Macondo."));
        libros.add(new Libro("Don Quijote de la Mancha", "Miguel de Cervantes", 1605, 863,
                Arrays.asList("Clásico", "Aventura"),
                "Historia de un hidalgo que enloquece leyendo novelas de caballería y decide convertirse en caballero andante."));
        libros.add(new Libro("1984", "George Orwell", 1949, 328,
                Arrays.asList("Distopía", "Ciencia ficción"),
                "Distopía sobre un régimen totalitario que controla todos los aspectos de la vida mediante la vigilancia constante."));
        libros.add(new Libro("El principito", "Antoine de Saint-Exupéry", 1943, 96,
                Arrays.asList("Fábula", "Filosofía"),
                "Fábula poética sobre un pequeño príncipe que viaja por diferentes planetas y reflexiona sobre la vida."));
        libros.add(new Libro("Rayuela", "Julio Cortázar", 1963, 736,
                Arrays.asList("Ficción", "Experimental"),
                "Novela experimental que permite ser leída en diferentes órdenes y desafía las convenciones narrativas."));
        libros.add(new Libro("Ficciones", "Jorge Luis Borges", 1944, 174,
                Arrays.asList("Cuentos", "Filosofía"),
                "Colección de cuentos que exploran temas como el tiempo, el infinito y la naturaleza de la realidad."));
        libros.add(new Libro("La sombra del viento", "Carlos Ruiz Zafón", 2001, 576,
                Arrays.asList("Misterio", "Drama"),
                "Misterio ambientado en la Barcelona de la posguerra sobre un libro que cambia la vida de su protagonista."));
        libros.add(new Libro("Crónica de una muerte anunciada", "Gabriel García Márquez", 1981, 122,
                Arrays.asList("Ficción", "Drama"),
                "Relato sobre un asesinato anunciado en un pequeño pueblo costeño."));
    }

    public void buscarLibro(String titulo) {
        if (titulo == null || titulo.trim().isEmpty()) {
            mensajeError.setValue("Ingrese un título");
            return;
        }

        String busqueda = titulo.toLowerCase().trim();
        for (Libro libro : libros) {
            if (libro.getTitulo().toLowerCase().contains(busqueda)) {
                libroEncontrado.setValue(libro);
                return;
            }
        }
        mensajeError.setValue("Libro no encontrado");
    }

    public List<Libro> filtrarLibros(String texto) {
        List<Libro> resultado = new ArrayList<>();
        if (texto == null || texto.trim().isEmpty()) {
            return resultado;
        }

        String busqueda = texto.toLowerCase().trim();
        for (Libro libro : libros) {
            if (libro.getTitulo().toLowerCase().contains(busqueda)) {
                resultado.add(libro);
            }
        }
        return resultado;
    }

    public LiveData<Libro> getLibroEncontrado() {
        return libroEncontrado;
    }

    public LiveData<String> getMensajeError() {
        return mensajeError;
    }

    public void limpiarError() {
        mensajeError.setValue(null);
    }
}
