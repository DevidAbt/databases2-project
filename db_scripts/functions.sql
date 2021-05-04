CREATE OR REPLACE PROCEDURE register(
    felhasznalonev IN VARCHAR2, 
    nev VARCHAR2,
    hash VARCHAR2,
    telefonszam VARCHAR2,
    email VARCHAR2,
    varos VARCHAR2,
    utca VARCHAR2,
    hazszam VARCHAR2
    )
IS
    lakcimId NUMBER;
BEGIN
    INSERT INTO SHOP_ADMIN.Lakcim (varos, utca, hazszam) VALUES (varos, utca, hazszam);
    SELECT MAX(id) INTO lakcimId FROM SHOP_ADMIN.Lakcim;
    INSERT INTO SHOP_ADMIN.Felhasznalo (felhasznalonev, nev, hash, telefonszam, email, lakcimId) 
    VALUES (felhasznalonev, nev, hash, telefonszam, email, lakcimId);
END;
/