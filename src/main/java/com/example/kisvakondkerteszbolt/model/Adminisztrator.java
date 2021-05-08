package com.example.kisvakondkerteszbolt.model;

public class Adminisztrator {
    public int id;
    public int termeketHozzaadhat;
    public int moderalhat;
    public String beosztas;
    public int szolgaltatastHozzaadhat;

    public Adminisztrator() {
    }

    public Adminisztrator(int id, int termeketHozzaadhat, int moderalhat, String beosztas, int szolgaltatastHozzaadhat) {
        this.id = id;
        this.termeketHozzaadhat = termeketHozzaadhat;
        this.moderalhat = moderalhat;
        this.beosztas = beosztas;
        this.szolgaltatastHozzaadhat = szolgaltatastHozzaadhat;
    }
}
