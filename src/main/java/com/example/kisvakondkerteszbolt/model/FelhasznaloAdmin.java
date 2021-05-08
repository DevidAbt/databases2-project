package com.example.kisvakondkerteszbolt.model;

public class FelhasznaloAdmin {
     public int id;
     public String felhasznalonev;
     public String nev;
     public String hash;
     public String telefonszam;
     public String email;
     public int lakcimId;
     public boolean isAdmin;
     public int termeketHozzaadhat;
     public int moderalhat;
     public int szolgaltatastHozzaadhat;

     public FelhasznaloAdmin() {
     }

     public FelhasznaloAdmin(int id, String felhasznalonev, String nev, String hash, String telefonszam, String email, int lakcimId, boolean isAdmin, int termeketHozzaadhat, int moderalhat, int szolgaltatastHozzaadhat) {
          this.id = id;
          this.felhasznalonev = felhasznalonev;
          this.nev = nev;
          this.hash = hash;
          this.telefonszam = telefonszam;
          this.email = email;
          this.lakcimId = lakcimId;
          this.isAdmin = isAdmin;
          this.termeketHozzaadhat = termeketHozzaadhat;
          this.moderalhat = moderalhat;
          this.szolgaltatastHozzaadhat = szolgaltatastHozzaadhat;
     }
}
