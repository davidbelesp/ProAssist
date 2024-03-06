package com.example.proassist.model;

import java.io.Serializable;

public class User implements Serializable {

    private Integer id;
    private String nombre;
    private String apellido;
    private String Especialidad;
    private String _password;

    public User(String nombre, String _password){
        this.nombre = nombre;
        this._password = _password;
    }

    public User(Integer id, String nombre, String apellido, String especialidad, String _password) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        Especialidad = especialidad;
        this._password = _password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getEspecialidad() {
        return Especialidad;
    }

    public void setEspecialidad(String especialidad) {
        Especialidad = especialidad;
    }

    public String get_password() {
        return _password;
    }

    public void set_password(String _password) {
        this._password = _password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", Especialidad='" + Especialidad + '\'' +
                ", _password='" + _password + '\'' +
                '}';
    }
}
