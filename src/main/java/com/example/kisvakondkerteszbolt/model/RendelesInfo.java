package com.example.kisvakondkerteszbolt.model;

public class RendelesInfo {
    public int rendelesSzam;
    public int felhasznaloId;
    public String mikor;
    public int kuponKod;
    public String felhasznalonev;
    public int termekid;
    public String termeknev;
    public int mennyiseg;
    public String nev;

    public RendelesInfo() {
    }

    public RendelesInfo(int rendelesSzam, int felhasznaloId, String mikor, int kuponKod, String felhasznalonev, int termekid, String termeknev, int mennyiseg, String nev) {
        this.rendelesSzam = rendelesSzam;
        this.felhasznaloId = felhasznaloId;
        this.mikor = mikor;
        this.kuponKod = kuponKod;
        this.felhasznalonev = felhasznalonev;
        this.termekid = termekid;
        this.termeknev = termeknev;
        this.mennyiseg = mennyiseg;
        this.nev = nev;
    }
}
