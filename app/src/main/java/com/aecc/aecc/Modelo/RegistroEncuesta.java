package com.aecc.aecc.Modelo;

import com.google.gson.Gson;

import java.io.Serializable;
import java.util.List;

public class RegistroEncuesta implements Serializable {
    private String mAnimo;
    private String mSleep;
    private boolean mDolor;
    private int mNivelDolor;
    private List<String> mPartes;
    private List<String> mActividades;
    private List<String> mSintomas;
    private String mEstadoFisico;

    public RegistroEncuesta() {

    }

    public String getAnimo() {
        return mAnimo;
    }

    public void setAnimo(String animo) {
        mAnimo = animo;
    }

    public String getSleep() {
        return mSleep;
    }

    public void setSleep(String sleep) {
        mSleep = sleep;
    }

    public boolean isDolor() {
        return mDolor;
    }

    public void setDolor(boolean dolor) {
        mDolor = dolor;
    }

    public int getNivelDolor() {
        return mNivelDolor;
    }

    public void setNivelDolor(int nivelDolor) {
        mNivelDolor = nivelDolor;
    }

    public List<String> getPartes() {
        return mPartes;
    }

    public void setPartes(List<String> partes) {
        mPartes = partes;
    }

    public List<String> getActividades() {
        return mActividades;
    }

    public void setActividades(List<String> actividades) {
        mActividades = actividades;
    }

    public List<String> getSintomas() {
        return mSintomas;
    }

    public void setSintomas(List<String> sintomas) {
        mSintomas = sintomas;
    }

    public String getEstadoFisico() {
        return mEstadoFisico;
    }

    public void setEstadoFisico(String estadoFisico) {
        mEstadoFisico = estadoFisico;
    }

    public String serializar(){
        Gson userJson = new Gson();
        return userJson.toJson(this);
    }
}
