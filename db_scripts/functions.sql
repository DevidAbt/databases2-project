DECLARE
CURSOR lekeres(p_tipus NUMBER)IS SELECT * FROM Termek WHERE termek.termekfajtaid=p_tipus;
PROCEDURE learazas(tipus in NUMBER,learaz in NUMBER) IS
    rekord Termek%ROWTYPE;
BEGIN
        OPEN lekeres(tipus);
        loop
            fetch lekeres INTO rekord;
            exit when lekeres%NOTFOUND;
            if (rekord.ar>learaz) then
                rekord.ar := rekord.ar-learaz;
            end if;
        end loop;
END;

DECLARE
PROCEDURE felarazas(tipus in NUMBER,felaraz in NUMBER) IS
    rekord Termek%ROWTYPE;
BEGIN
        OPEN lekeres(tipus);
        loop
            fetch lekeres INTO rekord;
            exit when lekeres%NOTFOUND;
            rekord.ar := rekord.ar+felaraz;
        end loop;
END;

DECLARE
PROCEDURE teljesit(rendel in NUMBER, kiszal in NUMBER) IS
BEGIN 
    UPDATE TELJESIT SET TELJESULT=1
    WHERE Teljesit.rendelesszam=rendel and Teljesit.kiszallitasiszam=kiszal;
END;


DECLARE
PROCEDURE rendeles_kiszallitas(felh IN NUMBER, kupon in NUMBER,atvevo in VARCHAR,lakcim in NUMBER) IS
BEGIN
    INSERT INTO Rendeles (felhasznaloid,mikor,kuponkod) VALUES(felh,sysdate,kupon);
    INSERT INTO Kiszallitas (felhasznaloid,atvevonev,lakcimid) VALUES(felh,atvevo,lakcim);
END;



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