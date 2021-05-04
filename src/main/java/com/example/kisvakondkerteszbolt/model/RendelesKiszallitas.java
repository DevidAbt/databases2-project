package com.example.kisvakondkerteszbolt.model;

public class RendelesKiszallitas {
    public int felhasznaloId;
    public int kuponKod;
    public String atvevoNev;
    public int lakcimId;

    public RendelesKiszallitas() {
    }

    public RendelesKiszallitas(int felhasznaloId, int kuponKod, String atvevoNev, int lakcimId) {
        this.felhasznaloId = felhasznaloId;
        this.kuponKod = kuponKod;
        this.atvevoNev = atvevoNev;
        this.lakcimId = lakcimId;
    }
}
