package com.example.kisvakondkerteszbolt.model;

public class Lakcim {
    private int id;
    private String varos;
    private String utca;
    private String hazszam;

    public Lakcim(int id, String varos, String utca, String hazszam) {
        this.id = id;
        this.varos = varos;
        this.utca = utca;
        this.hazszam = hazszam;
    }

    public int getId() {
        return id;
    }

    public String getVaros() {
        return varos;
    }

    public String getUtca() {
        return utca;
    }

    public String getHazszam() {
        return hazszam;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setVaros(String varos) {
        this.varos = varos;
    }

    public void setUtca(String utca) {
        this.utca = utca;
    }

    public void setHazszam(String hazszam) {
        this.hazszam = hazszam;
    }
}
