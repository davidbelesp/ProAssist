package com.example.proassist.model;

public class AddFaltaDto {

    private Integer idProfesor;
    private String fecha;
    private String hora;

    public AddFaltaDto(Integer idProfesor, String fecha) {
        this.idProfesor = idProfesor;
        this.fecha = fecha;
    }

    public AddFaltaDto(Integer idProfesor, String fecha, String hora) {
        this.idProfesor = idProfesor;
        this.fecha = fecha;
        this.hora = hora;
    }

    public Integer getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(Integer idProfesor) {
        this.idProfesor = idProfesor;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    @Override
    public String toString() {
        return "AddFaltaDto{" +
                "idProfesor=" + idProfesor +
                ", fecha='" + fecha + '\'' +
                ", hora='" + hora + '\'' +
                '}';
    }
}
