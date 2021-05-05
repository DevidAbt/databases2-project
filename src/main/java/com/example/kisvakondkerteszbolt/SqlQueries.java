package com.example.kisvakondkerteszbolt;

@SuppressWarnings("SpellCheckingInspection")
public class SqlQueries {
    // Felhasznalo
    public static String SELECT_USER = "SELECT id, felhasznalonev, nev, hash, telefonszam, email, lakcimId " +
            "FROM shop_admin.Felhasznalo WHERE id = ?";
    public static String SELECT_USER_BY_USERNAME = "SELECT id, felhasznalonev, nev, hash, telefonszam, email, lakcimId " +
            "FROM shop_admin.Felhasznalo WHERE felhasznalonev = ?";
    public static String INSERT_USER = "INSERT INTO SHOP_ADMIN.Felhasznalo (felhasznalonev, nev, hash, telefonszam, " +
            "email, lakcimId) VALUES (?, ?, ?, ?, ?, ?)";

    // Lakcim
    public static String SELECT_ALL_ADDRESSES = "SELECT id, varos, utca, hazszam FROM shop_admin.Lakcim";
    public static String SELECT_ADDRESSES_BY_ID = "SELECT id, varos, utca, hazszam FROM shop_admin.Lakcim WHERE id = ?";
    public static String INSERT_ADDRESS = "INSERT INTO SHOP_ADMIN.Lakcim (varos, utca, hazszam) VALUES (?, ?, ?)";

    // Kategoria
    public static String SELECT_CATEGORIES = "SELECT id, nev FROM SHOP_ADMIN.kategoria";

    // Termek
    public static String SELECT_PRODUCTS_BY_PRODUCTTYPE = "SELECT id, uzletId, termekFajtaId, kategoriaId, nev, ar, leiras " +
            "FROM SHOP_ADMIN.Termek WHERE termekFajtaId = ?";
    public static String SELECT_PRODUCT = "SELECT * FROM SHOP_ADMIN.Termek WHERE id = ?";

    // Szolgaltatas
    public static String SELECT_ALL_SERVICES = "SELECT * FROM SHOP_ADMIN.szolgaltatas";
    public static String SELECT_SERVICES_BY_ORDER_ID = "SELECT * FROM SHOP_ADMIN.Szolgaltatas WHERE id IN (SELECT szolgaltatasId FROM SHOP_ADMIN.RendelesSzolgaltatas WHERE rendelesSzam = ?)";

    // Ertekeles
    public static String SELECT_RATINGS = "SELECT id, felhasznaloId, termekId, szolgaltatasId, datum, szoveg, csillag \n" +
            "FROM SHOP_ADMIN.Ertekeles\n" +
            "WHERE termekId = ? OR szolgaltatasId = ?";
    public static String INSERT_RATING = "INSERT INTO SHOP_ADMIN.Ertekeles (felhasznaloId, termekId, szolgaltatasId, datum, szoveg, csillag)\n" +
            "VALUES(?, ?, ?, sysdate, ?, ?)";

    // Rendeles
    public static String SELECT_LAST_ORDER = "SELECT * FROM SHOP_ADMIN.Rendeles WHERE rendelesSzam = (SELECT MAX(rendelesSzam) FROM SHOP_ADMIN.Rendeles)";
    public static String SELECT_ORDERS_OF_USER = "SELECT * FROM SHOP_ADMIN.Rendeles WHERE felhasznaloid = ? ORDER BY rendelesSzam DESC";

    // RendelesTermek
    public static String INSERT_RENDELES_TERMEK = "INSERT INTO SHOP_ADMIN.RendelesTermek VALUES (?, ?, ?)";
    public static String SELECT_RENDELES_TERMEK = "SELECT * FROM SHOP_ADMIN.RendelesTermek WHERE rendelesSzam = ?";

    // RendelesSzolgaltatas
    public static String INSERT_RENDELES_SZOLGALTATAS = "INSERT INTO SHOP_ADMIN.RendelesSzolgaltatas VALUES (?, ?)";
    public static String SELECT_RENDELES_SZOLGALTATAS = "SELECT * FROM SHOP_ADMIN.RendelesSzolgaltatas WHERE rendelesSzam = ?";


    // Osszetett lekerdezesek //
    public static String SELECT_PRODUCTTYPE_BY_CATEGORY = "SELECT id, nev FROM SHOP_ADMIN.Termekfajta WHERE id IN " +
            "(SELECT termekFajtaId FROM shop_admin.Termek WHERE kategoriaId = ?)";
    public static String SELECT_PRODUCTS_INFO= "SELECT SHOP_ADMIN.Termek.id, SHOP_ADMIN.Kategoria.nev AS kategoria, " +
            "SHOP_ADMIN.Termekfajta.nev as termekFajta, uzletId, SHOP_ADMIN.Termek.nev, ar, leiras\n" +
            "FROM SHOP_ADMIN.Termek \n" +
            "INNER JOIN SHOP_ADMIN.Kategoria ON SHOP_ADMIN.Termek.kategoriaId = SHOP_ADMIN.Kategoria.id\n" +
            "INNER JOIN SHOP_ADMIN.Termekfajta ON SHOP_ADMIN.Termek.termekFajtaId = SHOP_ADMIN.Termekfajta.id\n" +
            "WHERE termekFajtaId = ?";
    public static String SELECT_SHOP_BY_PRODUCT = "SELECT id, varos, utca, hazszam FROM SHOP_ADMIN.Lakcim\n" +
            "WHERE id = (SELECT SHOP_ADMIN.Uzlet.lakcimId\n" +
            "FROM SHOP_ADMIN.Termek \n" +
            "INNER JOIN SHOP_ADMIN.Uzlet ON SHOP_ADMIN.Termek.uzletId = SHOP_ADMIN.Uzlet.id\n" +
            "WHERE SHOP_ADMIN.Termek.id = ?)";
    public static String SELECT_SHOP_INFO_BY_PRODUCT = "SELECT SHOP_ADMIN.Uzlet.id, varos, utca, hazszam, SHOP_ADMIN.UzletNyitvatartas.nyitas, SHOP_ADMIN.UzletNyitvatartas.zaras\n" +
            "FROM SHOP_ADMIN.Lakcim\n" +
            "INNER JOIN SHOP_ADMIN.Uzlet ON SHOP_ADMIN.Lakcim.id = SHOP_ADMIN.Uzlet.lakcimId\n" +
            "INNER JOIN SHOP_ADMIN.Termek ON SHOP_ADMIN.Termek.uzletId = SHOP_ADMIN.Uzlet.id\n" +
            "INNER JOIN SHOP_ADMIN.Uzletnyitvatartas ON SHOP_ADMIN.Uzlet.id = SHOP_ADMIN.Uzletnyitvatartas.uzletId\n" +
            "WHERE SHOP_ADMIN.Termek.id = ?";
    public static String SELECT_PRODUCTS_INFO_BY_NAME = "SELECT SHOP_ADMIN.Termek.id, SHOP_ADMIN.Kategoria.nev AS kategoria, \n" +
            "SHOP_ADMIN.Termekfajta.nev as termekFajta, uzletId, SHOP_ADMIN.Termek.nev, ar, leiras\n" +
            "FROM SHOP_ADMIN.Termek \n" +
            "INNER JOIN SHOP_ADMIN.Kategoria ON SHOP_ADMIN.Termek.kategoriaId = SHOP_ADMIN.Kategoria.id\n" +
            "INNER JOIN SHOP_ADMIN.Termekfajta ON SHOP_ADMIN.Termek.termekFajtaId = SHOP_ADMIN.Termekfajta.id\n" +
            "WHERE SHOP_ADMIN.Termek.nev LIKE ?";
    public static String SELECT_SHOPS = "Uzlet.id, UzletNyitvatartas.nyitas, UzletNyitvatartas.zaras, Lakcim.varos,Lakcim.utca,Lakcim.hazszam\n"+
            "FROM SHOP_ADMIN.Uzlet, SHOP_ADMIN.Lakcim, SHOP_ADMIN.UzletNyitvatartas\n"+
            "WHERE SHOP_ADMIN.Uzlet.id=SHOP_ADMIN.UzletNyitvatartas.uzletid and SHOP_ADMIN.Uzlet.lakcimid=SHOP_ADMIN.Lakcim.id\n"+
            "GROUP BY SHOP_ADMIN.Uzlet.id";
    public static String SELECT_ORDERS_IN_12_MONTHS= "SELECT SHOP_ADMIN.Rendeles.*,SHOP_ADMIN.Felhasznalo.felhasznalonev,  SHOP_ADMIN.RendelesTermek.termekid, SHOP_ADMIN.Termek.nev,  SHOP_ADMIN.RendelesTermek.mennyiseg, SHOP_ADMIN.RendelesSzolgaltatas.szolgaltatasid, SHOP_ADMIN.Szolgaltatas.nev\n"+
            "FROM SHOP_ADMIN.Rendeles\n"+
            "INNER JOIN SHOP_ADMIN.Felhasznalo ON SHOP_ADMIN.rendeles.felhasznaloid=SHOP_ADMIN.Felhasznalo.id\n"+
            "LEFT JOIN SHOP_ADMIN.RendelesTermek ON SHOP_ADMIN.Rendeles.rendelesszam=SHOP_ADMIN.RendelesTermek.rendelesszam\n"+
            "LEFT JOIN SHOP_ADMIN.RendelesSzolgaltatas ON SHOP_ADMIN.Rendeles.rendelesszam=SHOP_ADMIN.RendelesSzolgaltatas.rendelesszam\n"+
            "LEFT JOIN SHOP_ADMIN.Termek ON SHOP_ADMIN.Rendelestermek.termekid=SHOP_ADMIN.Termek.id\n"+
            "LEFT JOIN SHOP_ADMIN.Szolgaltatas ON SHOP_ADMIN.Rendelesszolgaltatas.szolgaltatasid=SHOP_ADMIN.Szolgaltatas.id\n"+
            "WHERE rendeles.mikor > add_months(sysdate, -12)";
    public static String SELECT_ORDERS_IN_6_MONTS = "SELECT SHOP_ADMIN.Rendeles.*,SHOP_ADMIN.Felhasznalo.felhasznalonev,  SHOP_ADMIN.RendelesTermek.termekid, SHOP_ADMIN.Termek.nev,  SHOP_ADMIN.RendelesTermek.mennyiseg, SHOP_ADMIN.RendelesSzolgaltatas.szolgaltatasid, SHOP_ADMIN.Szolgaltatas.nev\n"+
            "FROM SHOP_ADMIN.Rendeles\n"+
            "INNER JOIN SHOP_ADMIN.Felhasznalo ON SHOP_ADMIN.rendeles.felhasznaloid=SHOP_ADMIN.Felhasznalo.id\n"+
            "LEFT JOIN SHOP_ADMIN.RendelesTermek ON SHOP_ADMIN.Rendeles.rendelesszam=SHOP_ADMIN.RendelesTermek.rendelesszam\n"+
            "LEFT JOIN SHOP_ADMIN.RendelesSzolgaltatas ON SHOP_ADMIN.Rendeles.rendelesszam=SHOP_ADMIN.RendelesSzolgaltatas.rendelesszam\n"+
            "LEFT JOIN SHOP_ADMIN.Termek ON SHOP_ADMIN.Rendelestermek.termekid=SHOP_ADMIN.Termek.id\n"+
            "LEFT JOIN SHOP_ADMIN.Szolgaltatas ON SHOP_ADMIN.Rendelesszolgaltatas.szolgaltatasid=SHOP_ADMIN.Szolgaltatas.id\n"+
            "WHERE rendeles.mikor > add_months(sysdate, -6)";

    public static String SELECT_ORDERS_IN_3_MONTHS = "SELECT SHOP_ADMIN.Rendeles.*,SHOP_ADMIN.Felhasznalo.felhasznalonev,  SHOP_ADMIN.RendelesTermek.termekid, SHOP_ADMIN.Termek.nev,  SHOP_ADMIN.RendelesTermek.mennyiseg, SHOP_ADMIN.RendelesSzolgaltatas.szolgaltatasid, SHOP_ADMIN.Szolgaltatas.nev\n"+
            "FROM SHOP_ADMIN.Rendeles\n"+
            "INNER JOIN SHOP_ADMIN.Felhasznalo ON SHOP_ADMIN.rendeles.felhasznaloid=SHOP_ADMIN.Felhasznalo.id\n"+
            "LEFT JOIN SHOP_ADMIN.RendelesTermek ON SHOP_ADMIN.Rendeles.rendelesszam=SHOP_ADMIN.RendelesTermek.rendelesszam\n"+
            "LEFT JOIN SHOP_ADMIN.RendelesSzolgaltatas ON SHOP_ADMIN.Rendeles.rendelesszam=SHOP_ADMIN.RendelesSzolgaltatas.rendelesszam\n"+
            "LEFT JOIN SHOP_ADMIN.Termek ON SHOP_ADMIN.Rendelestermek.termekid=SHOP_ADMIN.Termek.id\n"+
            "LEFT JOIN SHOP_ADMIN.Szolgaltatas ON SHOP_ADMIN.Rendelesszolgaltatas.szolgaltatasid=SHOP_ADMIN.Szolgaltatas.id\n"+
            "WHERE rendeles.mikor > add_months(sysdate, -3)";
    public static String SELECT_ORDER_DELIVERY_BY_USER ="SELECT SHOP_ADMIN.Teljesit.*, SHOP_ADMIN.Felhasznalo.nev, SHOP_ADMIN.Rendeles.mikor, SHOP_ADMIN.Rendeles.kuponkod, SHOP_ADMIN.Kiszallitas.atvevonev, SHOP_ADMIN.Lakcim.varos, SHOP_ADMIN.Lakcim.utca, SHOP_ADMIN.Lakcim.Hazszam\n"+
            "FROM SHOP_ADMIN.Teljesit\n"+
            "INNER JOIN SHOP_ADMIN.Rendeles ON SHOP_ADMIN.Teljesit.rendelesszam=SHOP_ADMIN.Rendeles.rendelesszam\n"+
            "INNER JOIN SHOP_ADMIN.Kiszallitas ON SHOP_ADMIN.Teljesit.kiszallitasiszam=SHOP_ADMIN.Kiszallitas.kiszallitasiszam\n"+
            "INNER JOIN SHOP_ADMIN.Felhasznalo ON SHOP_ADMIN.Rendeles.felhasznaloid=SHOP_ADMIN.Felhasznalo.id\n"+
            "INNER JOIN SHOP_ADMIN.Lakcim ON SHOP_ADMIN.Kiszallitas.lakcimid=SHOP_ADMIN.Lakcim.id\n"+
            "WHERE SHOP_ADMIN.Felhasznalo.id=?";
    public static String SELECT_RATINGS_BY_USER = "SELECT SHOP_ADMIN.Ertekeles.*, SHOP_ADMIN.Felhasznalo.felhasznalonev,SHOP_ADMIN.Felhasznalo.email, SHOP_ADMIN.Termek.nev, SHOP_ADMIN.Szolgaltatas.nev\n"+
            "FROM SHOP_ADMIN.Ertekeles\n"+
            "INNER JOIN SHOP_ADMIN.Felhasznalo ON SHOP_ADMIN.Ertekeles.felhasznaloid=SHOP_ADMIN.felhasznalo.id\n"+
            "LEFT JOIN SHOP_ADMIN.Termek ON SHOP_ADMIN.Ertekeles.termekid=SHOP_ADMIN.Termek.id\n"+
            "LEFT JOIN SHOP_ADMIN.Szolgaltatas ON SHOP_ADMIN.Ertekeles.szolgaltatasid=SHOP_ADMIN.Szolgaltatas.id\n"+
            "WHERE SHOP_ADMIN.Felhasznalo.id=?";
    public static String SELECT_ORDER_BY_ADDRESS = "SELECT SHOP_ADMIN.Kiszallitas.kiszallitasiszam, SHOP_ADMIN.Kiszallitas.atvevonev, SHOP_ADMIN.Kiszallitas.lakcimid, SHOP_ADMIN.Felhasznalo.nev, SHOP_ADMIN.Felhasznalo.id, SHOP_ADMIN.Rendeles.rendelesszam, SHOP_ADMIN.Rendeles.mikor, SHOP_ADMIN.Rendeles.kuponkod, SHOP_ADMIN.Lakcim.varos, SHOP_ADMIN.Lakcim.utca, SHOP_ADMIN.Lakcim.hazszam\n"+
            "FROM SHOP_ADMIN.Rendeles\n"+
            "INNER JOIN SHOP_ADMIN.Felhasznalo ON SHOP_ADMIN.Rendeles.felhasznaloid=SHOP_ADMIN.felhasznalo.id\n"+
            "INNER JOIN SHOP_ADMIN.Kiszallitas ON SHOP_ADMIN.Felhasznalo.id=SHOP_ADMIN.Kiszallitas.felhasznaloid\n"+
            "INNER JOIN SHOP_ADMIN.Lakcim ON SHOP_ADMIN.Kiszallitas.lakcimid=SHOP_ADMIN.Lakcim.id\n"+
            "WHERE SHOP_ADMIN.Lakcim.id=?";


    // Fuggvenyhivas //
    public static String REGISTER = "BEGIN\n" +
            "    REGISTER(?, ?, ?, ?, ?, ?, ?, ?);\n" +
            "END;";

    public static String RENDELES_KISZALLITAS = "BEGIN\n" +
            "    rendeles_kiszallitas(?, ?, ?, ?);\n" +
            "END;";

}
