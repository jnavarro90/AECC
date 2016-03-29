package com.aecc.aecc.Modelo;

public class Paciente {
    private String nombre_usuario;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String dni;
    private int idTerapeuta;

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public int getIdTerapeuta() {
        return idTerapeuta;
    }

    public void setIdTerapeuta(int idTerapeuta) {
        this.idTerapeuta = idTerapeuta;
    }
}
