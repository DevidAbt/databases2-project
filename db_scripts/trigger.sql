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
