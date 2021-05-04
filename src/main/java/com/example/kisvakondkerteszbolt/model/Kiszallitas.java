package com.example.kisvakondkerteszbolt.model;

public class Kiszallitas {
    public int kiszallitasiSzam;
    public int felhasznaloId;
    public String atvevoNev;
    public int lakcimId;

    public Kiszallitas() {
    }

    public Kiszallitas(int kiszallitasiSzam, int felhasznaloId, String atvevoNev, int lakcimId) {
        this.kiszallitasiSzam = kiszallitasiSzam;
        this.felhasznaloId = felhasznaloId;
        this.atvevoNev = atvevoNev;
        this.lakcimId = lakcimId;
    }
}
