package com.example.kisvakondkerteszbolt.model;

public class UzletInfo {
    public int id;
    public String varos;
    public String utca;
    public String hazszam;
    public String nyitas;
    public String zaras;

    public UzletInfo() {
    }

    public UzletInfo(int id, String varos, String utca, String hazszam, String  nyitas, String zaras) {
        this.id = id;
        this.varos = varos;
        this.utca = utca;
        this.hazszam = hazszam;
        this.nyitas = nyitas;
        this.zaras = zaras;
    }
}
