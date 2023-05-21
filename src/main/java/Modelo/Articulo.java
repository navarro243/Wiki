/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author user
 */
public class Articulo {
    private int id;
    private String titulo;
    private String contenido;
    private int id_Wiki;

    public Articulo() {
    }

    public Articulo(int id, String titulo, String contenido, int id_Wiki) {
        this.id = id;
        this.titulo = titulo;
        this.contenido = contenido;
        this.id_Wiki = id_Wiki;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public int getId_Wiki() {
        return id_Wiki;
    }

    public void setId_Wiki(int id_Wiki) {
        this.id_Wiki = id_Wiki;
    }
    
}
