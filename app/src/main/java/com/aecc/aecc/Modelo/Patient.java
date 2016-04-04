package com.aecc.aecc.Modelo;

import java.io.Serializable;

public class Patient implements Serializable {
    private String mUsuario;
    private String mNombre;
    private String mApellido1;
    private String mApellido2;
    private String mDni;
    private String mEmail;
    private int mIdTerapeuta;


    public Patient() {

    }

    public Patient(String usuario, String nombre, String apellido1, String apellido2, String dni, String email, int idTerapeuta) {
        mUsuario = usuario;
        mNombre = nombre;
        mApellido1 = apellido1;
        mApellido2 = apellido2;
        mDni = dni;
        mEmail = email;
        mIdTerapeuta = idTerapeuta;
    }

    public String getUsuario() {
        return mUsuario;
    }

    public void setUsuario(String usuario) {
        this.mUsuario = usuario;
    }

    public String getNombre() {
        return mNombre;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public void setNombre(String nombre) {
        this.mNombre = nombre;
    }

    public String getApellido1() {
        return mApellido1;
    }

    public void setApellido1(String apellido1) {
        this.mApellido1 = apellido1;
    }

    public String getApellido2() {
        return mApellido2;
    }

    public void setApellido2(String apellido2) {
        this.mApellido2 = apellido2;
    }

    public String getDni() {
        return mDni;
    }

    public void setDni(String dni) {
        this.mDni = dni;
    }

    public int getIdTerapeuta() {
        return mIdTerapeuta;
    }

    public void setIdTerapeuta(int idTerapeuta) {
        this.mIdTerapeuta = idTerapeuta;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "mUsuario='" + mUsuario + '\'' +
                ", mNombre='" + mNombre + '\'' +
                ", mApellido1='" + mApellido1 + '\'' +
                ", mApellido2='" + mApellido2 + '\'' +
                ", mDni='" + mDni + '\'' +
                ", mEmail='" + mEmail + '\'' +
                ", mIdTerapeuta=" + mIdTerapeuta +
                '}';
    }
}
