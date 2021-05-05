package com.example.kisvakondkerteszbolt.model;

import java.util.List;

public class OrderInfo {
    public int rendelesSzam;
    public String mikor;
    public List<TermekMennyiseggel> termekek;
    public List<Szolgaltatas> szolgaltatasok;

    public OrderInfo() {
    }

    public OrderInfo(int rendelesSzam, String mikor, List<TermekMennyiseggel> termekek, List<Szolgaltatas> szolgaltatasok) {
        this.rendelesSzam = rendelesSzam;
        this.mikor = mikor;
        this.termekek = termekek;
        this.szolgaltatasok = szolgaltatasok;
    }
}
