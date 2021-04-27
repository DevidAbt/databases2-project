package com.example.kisvakondkerteszbolt.model;

public class TermekInfo {
    public int id;
    public int uzletId;
    public String termekFajta;
    public String kategoria;
    public String nev;
    public int ar;
    public String leiras;

    public TermekInfo() {
    }

    public TermekInfo(int id, int uzletId, String termekFajta, String kategoria, String nev, int ar, String leiras) {
        this.id = id;
        this.uzletId = uzletId;
        this.termekFajta = termekFajta;
        this.kategoria = kategoria;
        this.nev = nev;
        this.ar = ar;
        this.leiras = leiras;
    }
}
