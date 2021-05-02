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

CREATE TRIGGER JelszovaltasNaplozas
BEFORE UPDATE OF hash
ON Felhasznalo
REFERENCING NEW AS NEW OLD AS OLD
FOR EACH ROW
BEGIN
    INSERT INTO JelszovaltasNaplo VALUES(SYSDATE, :NEW.id, :OLD.hash, :NEW.hash);
END;
/

CREATE TRIGGER CimetEllenoriz
BEFORE INSERT
ON Lakcim
REFERENCING NEW AS NEW OLD AS OLD
DECLARE
CURSOR cimek IS SELECT * FROM LAKCIM;
BEGIN
  FOR v IN cimek LOOP
    IF v.varos= :NEW.varos
    AND v.utca= :NEW.utca
    AND v.hazszam=:NEW.hazszam THEN
        raise_application_error(-20100,'Ez a lakcim már létezik');
    END IF;
  END LOOP;
END;
/
--Ezt meg nézze meg valaki, hogy nála működik-e
CREATE OR REPLACE TRIGGER RendelesKiszallitas
AFTER INSERT
ON Rendeles
FOR EACH ROW
DECLARE
    atvevo Kiszallitas.atvevonev%TYPE;
    lakcim Kiszallitas.lakcimid%TYPE;
BEGIN
    atvevo := &y;
    lakcim := &x;
    INSERT INTO Kiszallitas VALUES(:NEW.felhasznaloid,atvevo,lakcimid);
END;
/

/*DECLARE
  seq_creat varchar2(2000);
  trigg_create varchar2(2000);  
BEGIN
  FOR i IN (SELECT table_name
              FROM user_tables
              WHERE table_name in ('ERTEKELES', 'FELHASZNALO', 'KATEGORIA','LAKCIM','SZOLGALTATAS', 'TERMEK','TERMEKFAJTA','UZLET') )loop
    seq_creat := 'CREATE SEQUENCE '||'SEQ_'||i.table_name||'_ID '||CHR(13)||' START WITH 1 INCREMENT BY 1 NOCYCLE ';
    EXECUTE IMMEDIATE seq_creat;
    trigg_create := 'CREATE OR REPLACE TRIGGER '||'TRG_'||i.Table_name||'_ID_BI '||' BEFORE INSERT ON '||i.table_name||' FOR EACH ROW '||CHR(13)||'BEGIN '||CHR(13)||' SELECT '||'SEQ_'||i.table_name||'_ID'||'.NEXTVAL'||CHR(13)||' INTO :new.SN'||CHR(13)||' FROM   dual;'||CHR(13)||'END; ';
                    EXECUTE IMMEDIATE trigg_create;
 END LOOP;
END;
/ 

Ebben valami olyan hiba vanm amit nem találok, de már elküldtem a tanárnak
*/