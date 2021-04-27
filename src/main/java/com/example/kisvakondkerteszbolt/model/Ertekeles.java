package com.example.kisvakondkerteszbolt.model;

import java.sql.Date;

public class Ertekeles {
    public int id;
    public int felhasznaloId;
    public int termekId;
    public int szolgaltatasId;
    public String datum;
    public String szoveg;
    public int csillag;

    public Ertekeles() {
    }

    public Ertekeles(int id, int felhasznaloId, int termekId, int szolgaltatasId, String datum, String szoveg, int csillag) {
        this.id = id;
        this.felhasznaloId = felhasznaloId;
        this.termekId = termekId;
        this.szolgaltatasId = szolgaltatasId;
        this.datum = datum;
        this.szoveg = szoveg;
        this.csillag = csillag;
    }
}
