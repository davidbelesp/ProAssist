package com.example.proassist.model;

import java.util.Date;

public class Falta {

    private String nombre;
    private String apellido;
    private Integer hora;
    private String grupo;

    public Falta(String nombre, String apellido, Integer hora, String grupo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.hora = hora;
        this.grupo = grupo;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Integer getHora() {
        return hora;
    }

    public void setHora(Integer hora) {
        this.hora = hora;
    }

    @Override
    public String toString() {
        return "Falta{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", hora=" + hora +
                ", grupo='" + grupo + '\'' +
                '}';
    }
}
