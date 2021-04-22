CREATE TABLE Lakcim (
    id NUMBER PRIMARY KEY,
    varos VARCHAR2(25) NOT NULL,
    utca VARCHAR2(25) NOT NULL,
    hazszam VARCHAR2(10) NOT NULL
);

CREATE TABLE Felhasznalo (
    id NUMBER PRIMARY KEY,
    felhasznalonev VARCHAR2(25) UNIQUE,
    nev VARCHAR2(50) NOT NULL,
    hash VARCHAR2(50) NOT NULL,
    so VARCHAR2(8) NOT NULL,
    telefonszam VARCHAR2(13),
    email VARCHAR2(40) NOT NULL,
    lakcimId NUMBER REFERENCES Lakcim(id) ON DELETE SET NULL
);

CREATE TABLE Adminisztrator (
    id NUMBER NOT NULL REFERENCES Felhasznalo(id) ON DELETE CASCADE,
    termeketHozzaadhat NUMBER(1) NOT NULL,
    moderalhat NUMBER(1) NOT NULL,
    beosztas VARCHAR2(25) NOT NULL,
    szolgaltatastHozzaadhat NUMBER(1) NOT NULL
);

CREATE TABLE Uzlet (
    id NUMBER PRIMARY KEY,
    lakcimId NUMBER REFERENCES Lakcim(id) ON DELETE SET NULL
);

CREATE TABLE UzletNyitvatartas (
    uzletId NUMBER PRIMARY KEY REFERENCES Uzlet(id) ON DELETE CASCADE,
    nyitas TIMESTAMP NOT NULL,
    zaras TIMESTAMP NOT NULL
);

CREATE TABLE Kupon (
    kod NUMBER PRIMARY KEY,
    kedvezmeny NUMBER NOT NULL,
    lejaratiDatum DATE NOT NULL
);

CREATE TABLE Rendeles (
    rendelesSzam NUMBER PRIMARY KEY,
    felhasznaloId NUMBER NOT NULL REFERENCES Felhasznalo(id) ON DELETE CASCADE,
    mikor DATE NOT NULL,
    kuponKod NUMBER REFERENCES Kupon(kod) ON DELETE SET NULL
);

-- Kategória: a termékek nagyobb csoportba sorolása, ami a nem céltudatos, csak nézelődni kívánó felhasználók számára lehet hasznos.
CREATE TABLE Kategoria (
    id NUMBER PRIMARY KEY,
    nev VARCHAR2(50) NOT NULL
);

--Termékfajta: a termékek szűkebb kategóriákba való besorolása a minél pontosabb szűrés/keresés megkönnyítése érdekében.
CREATE TABLE TermekFajta (
    id NUMBER PRIMARY KEY,
    nev VARCHAR2(50) NOT NULL
);

CREATE TABLE Termek (
    id NUMBER PRIMARY KEY,
    uzletId NUMBER REFERENCES Uzlet(id) ON DELETE SET NULL,
    termekFajtaId NUMBER REFERENCES TermekFajta(id) ON DELETE SET NULL,
    kategoriaId NUMBER REFERENCES Kategoria(id) ON DELETE SET NULL,
    nev VARCHAR2(50) NOT NULL,
    ar NUMBER NOT NULL,
    leiras VARCHAR2(280) NOT NULL
);

CREATE TABLE RendelesTermek (
    rendelesSzam NUMBER NOT NULL REFERENCES Rendeles(rendelesSzam) ON DELETE CASCADE,
    termekId NUMBER NOT NULL REFERENCES Termek(id) ON DELETE CASCADE,
    mennyiseg NUMBER NOT NULL,
    PRIMARY KEY (rendelesSzam, termekId)
);

CREATE TABLE Szolgaltatas (
    id NUMBER PRIMARY KEY,
    nev VARCHAR2(50) NOT NULL,
    ar NUMBER NOT NULL,
    leiras VARCHAR2(280) NOT NULL
);

CREATE TABLE RendelesSzolgaltatas (
    rendelesSzam NUMBER NOT NULL REFERENCES Rendeles(rendelesSzam) ON DELETE CASCADE,
    szolgaltatasId NUMBER NOT NULL REFERENCES Szolgaltatas(id) ON DELETE CASCADE,
    PRIMARY KEY (rendelesSzam, szolgaltatasId)
);

CREATE TABLE Kiszallitas (
    kiszallitasiSzam NUMBER PRIMARY KEY,
    felhasznaloId NUMBER NOT NULL REFERENCES Felhasznalo(id) ON DELETE CASCADE,
    atvevoNev VARCHAR2(50) NOT NULL,
    lakcimId NUMBER NOT NULL REFERENCES Lakcim(id) ON DELETE CASCADE
);

CREATE TABLE Ertekeles (
    id NUMBER PRIMARY KEY,
    felhasznaloId NUMBER NOT NULL REFERENCES Felhasznalo(id) ON DELETE CASCADE,
    termekId NUMBER REFERENCES Termek(id) ON DELETE CASCADE,
    szolgaltatasId NUMBER REFERENCES Szolgaltatas(id) ON DELETE CASCADE,
    datum DATE NOT NULL,
    szoveg VARCHAR2(280),
    csillag NUMBER(1) NOT NULL
);

CREATE TABLE Teljesit (
    rendelesSzam NUMBER REFERENCES Rendeles(rendelesSzam) ON DELETE CASCADE,
    kiszallitasiSzam NUMBER REFERENCES Kiszallitas(kiszallitasiSzam) ON DELETE CASCADE,
    teljesult NUMBER(1) NOT NULL,
    PRIMARY KEY (rendelesSzam, kiszallitasiSzam)
);
