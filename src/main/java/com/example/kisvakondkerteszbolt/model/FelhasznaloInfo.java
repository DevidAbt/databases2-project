package com.example.kisvakondkerteszbolt.model;

public class FelhasznaloInfo {
     public String felhasznalonev;
     public String nev;
     public String hash;
     public String telefonszam;
     public String email;
     public String varos;
     public String utca;
     public String hazszam;

     public FelhasznaloInfo() {
     }

     public FelhasznaloInfo(String felhasznalonev, String nev, String hash, String telefonszam, String email, String varos, String utca, String hazszam) {
          this.felhasznalonev = felhasznalonev;
          this.nev = nev;
          this.hash = hash;
          this.telefonszam = telefonszam;
          this.email = email;
          this.varos = varos;
          this.utca = utca;
          this.hazszam = hazszam;
     }
}
