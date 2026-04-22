package com.example.tp3moviles.model;

import java.util.List;

public class Libro {
    private String titulo;
    private String autor;
    private int anio;
    private int paginas;
    private List<String> generos;
    private String descripcion;

    public Libro(String titulo, String autor, int anio, int paginas, List<String> generos, String descripcion) {
        this.titulo = titulo;
        this.autor = autor;
        this.anio = anio;
        this.paginas = paginas;
        this.generos = generos;
        this.descripcion = descripcion;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public int getAnio() {
        return anio;
    }

    public int getPaginas() {
        return paginas;
    }

    public List<String> getGeneros() {
        return generos;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
