package com.example.kisvakondkerteszbolt.model;

public class RendelesTermek {
    public int rendelesSzam;
    public int termekId;
    public int mennyiseg;

    public RendelesTermek() {
    }

    public RendelesTermek(int rendelesSzam, int termekId, int mennyiseg) {
        this.rendelesSzam = rendelesSzam;
        this.termekId = termekId;
        this.mennyiseg = mennyiseg;
    }
}
