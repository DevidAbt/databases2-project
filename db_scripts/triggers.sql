CREATE TABLE Felhasznaloszamlalo (ertek NUMBER);
CREATE TABLE JelszovaltasNaplo (datum DATE,felhasznaloid NUMBER, regihash VARCHAR(50), ujhash VARCHAR(50));
INSERT INTO felhasznaloszamlalo VALUES(30);

CREATE TRIGGER FelhasznaloRegisztral
BEFORE INSERT 
ON Felhasznalo
BEGIN
    UPDATE Felhasznaloszamlalo SET ertek = ertek+1;
END;
/

CREATE TRIGGER FelhasznaloTorol
BEFORE DELETE
ON Felhasznalo
BEGIN
    UPDATE Felhasznaloszamlalo SET ertek = ertek-1;
END;
/

CREATE OR REPLACE TRIGGER JelszovaltasNaplozas
BEFORE UPDATE OF hash
ON Felhasznalo
REFERENCING NEW AS NEW OLD AS OLD
FOR EACH ROW
BEGIN
    INSERT INTO JelszovaltasNaplo VALUES(SYSDATE, :NEW.id, :OLD.hash, :NEW.hash);
END;
/
CREATE OR REPLACE TRIGGER CimetEllenoriz
BEFORE INSERT
ON Lakcim
FOR EACH ROW
BEGIN
DECLARE
    l_exists NUMBER;
    BEGIN
    SELECT COUNT(*) INTO l_exists FROM Lakcim WHERE Lakcim.varos=:New.varos AND Lakcim.hazszam=:New.Hazszam AND Lakcim.hazszam=:NEW.hazszam;
    IF l_exists = 1 THEN raise_application_error(-20100,'Ez a lakcim már létezik');
    END IF;
    END;
END;
/

CREATE OR REPLACE TRIGGER Termek_check
BEFORE INSERT
ON RendelesTermek
FOR EACH ROW
DECLARE
l_exists NUMBER;
BEGIN
        SELECT COUNT(*) INTO l_exists FROM TERMEK WHERE Termek.id = :NEW.termekid;
        IF l_exists=0 THEN raise_application_error(-20200,'Nem létezik a termék'); END IF;
END;
/


CREATE SEQUENCE rendeles_sequence;
CREATE OR REPLACE TRIGGER rendeles_insert
  BEFORE INSERT ON Rendeles
  FOR EACH ROW
BEGIN
  SELECT rendeles_sequence.nextval
  INTO :new.rendelesszam
  FROM dual;
END;

CREATE SEQUENCE kiszallitas_sequence;
CREATE OR REPLACE TRIGGER kiszallitas_insert
  BEFORE INSERT ON Kiszallitas
  FOR EACH ROW
BEGIN
  SELECT kiszallitas_sequence.nextval
  INTO :new.kiszallitasiszam
  FROM dual;
END;

CREATE SEQUENCE felhasznalo_sequence;
CREATE OR REPLACE TRIGGER felhasznalo_insert
  BEFORE INSERT ON Felhasznalo
  FOR EACH ROW
BEGIN
  SELECT felhasznalo_sequence.nextval
  INTO :new.id
  FROM dual;
END;

CREATE SEQUENCE ertekeles_sequence;
CREATE OR REPLACE TRIGGER ertekeles_insert
  BEFORE INSERT ON Ertekeles
  FOR EACH ROW
BEGIN
  SELECT ertekeles_sequence.nextval
  INTO :new.id
  FROM dual;
END;

CREATE SEQUENCE kategoria_sequence;
CREATE OR REPLACE TRIGGER kategoria_insert
  BEFORE INSERT ON Kategoria
  FOR EACH ROW
BEGIN
  SELECT kategoria_sequence.nextval
  INTO :new.id
  FROM dual;
END;

CREATE SEQUENCE termekfajta_sequence;
CREATE OR REPLACE TRIGGER termekfajta_insert
  BEFORE INSERT ON TermekFajta
  FOR EACH ROW
BEGIN
  SELECT termekfajta_sequence.nextval
  INTO :new.id
  FROM dual;
END;

CREATE SEQUENCE uzlet_sequence;
CREATE OR REPLACE TRIGGER uzlet_insert
  BEFORE INSERT ON Uzlet
  FOR EACH ROW
BEGIN
  SELECT uzlet_sequence.nextval
  INTO :new.id
  FROM dual;
END;

CREATE SEQUENCE kupon_sequence;
CREATE OR REPLACE TRIGGER kupon_insert
  BEFORE INSERT ON Kupon
  FOR EACH ROW
BEGIN
  SELECT kupon_sequence.nextval
  INTO :new.kod
  FROM dual;
END;

CREATE SEQUENCE lakcim_sequence;
CREATE OR REPLACE TRIGGER lakcim_insert
  BEFORE INSERT ON Lakcim
  FOR EACH ROW
BEGIN
  SELECT lakcim_sequence.nextval
  INTO :new.id
  FROM dual;
END;

CREATE SEQUENCE szolgaltatas_sequence;
CREATE OR REPLACE TRIGGER szolgaltatas_insert
  BEFORE INSERT ON Szolgaltatas
  FOR EACH ROW
BEGIN
  SELECT szolgaltatas_sequence.nextval
  INTO :new.id
  FROM dual;
END;

CREATE SEQUENCE termek_sequence;
CREATE OR REPLACE TRIGGER termek_insert
  BEFORE INSERT ON Termek
  FOR EACH ROW
BEGIN
  SELECT termek_sequence.nextval
  INTO :new.id
  FROM dual;
END;

CREATE SEQUENCE uzlet_sequence;
CREATE OR REPLACE TRIGGER uzlet_insert
  BEFORE INSERT ON Uzlet
  FOR EACH ROW
BEGIN
  SELECT uzlet_sequence.nextval
  INTO :new.id
  FROM dual;
END;