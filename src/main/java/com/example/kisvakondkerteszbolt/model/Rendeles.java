package com.example.kisvakondkerteszbolt.model;

public class Rendeles {
    public int rendelesSzam;
    public int felhasznaloId;
    public String mikor;
    public int kuponKod;

    public Rendeles() {
    }

    public Rendeles(int rendelesSzam, int felhasznaloId, String mikor, int kuponKod) {
        this.rendelesSzam = rendelesSzam;
        this.felhasznaloId = felhasznaloId;
        this.mikor = mikor;
        this.kuponKod = kuponKod;
    }
}
