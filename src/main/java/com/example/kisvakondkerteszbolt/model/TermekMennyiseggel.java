package com.example.kisvakondkerteszbolt.model;

public class TermekMennyiseggel {
    public int id;
    public int uzletId;
    public int termekFajtaId;
    public int kategoriaId;
    public String nev;
    public int ar;
    public String leiras;
    public int mennyiseg;

    public TermekMennyiseggel() {
    }

    public TermekMennyiseggel(int id, int uzletId, int termekFajtaId, int kategoriaId, String nev, int ar, String leiras, int mennyiseg) {
        this.id = id;
        this.uzletId = uzletId;
        this.termekFajtaId = termekFajtaId;
        this.kategoriaId = kategoriaId;
        this.nev = nev;
        this.ar = ar;
        this.leiras = leiras;
        this.mennyiseg = mennyiseg;
    }
}
