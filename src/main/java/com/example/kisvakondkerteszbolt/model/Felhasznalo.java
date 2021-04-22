package com.example.kisvakondkerteszbolt.model;

public class Felhasznalo {
     public int id;
     public String felhasznalonev;
     public String nev;
     public String hash;
     public String so;
     public String telefonszam;
     public String email;
     public int lakcimId;

     public Felhasznalo() {
     }

     public Felhasznalo(int id, String felhasznalonev, String nev, String hash, String telefonszam, String email, int lakcimId) {
          this.id = id;
          this.felhasznalonev = felhasznalonev;
          this.nev = nev;
          this.hash = hash;
          this.telefonszam = telefonszam;
          this.email = email;
          this.lakcimId = lakcimId;
     }
}
