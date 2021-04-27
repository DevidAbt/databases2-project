package com.example.kisvakondkerteszbolt;

@SuppressWarnings("SpellCheckingInspection")
public class SqlQueries {
    // Felhasznalo
    public static String SELECT_USER = "SELECT id, felhasznalonev, nev, hash, telefonszam, email, lakcimId " +
            "FROM shop_admin.Felhasznalo WHERE id = ?";
    public static String SELECT_USER_BY_USERNAME = "SELECT id, felhasznalonev, nev, hash, telefonszam, email, lakcimId " +
            "FROM shop_admin.Felhasznalo WHERE felhasznalonev = ?";
    public static String INSERT_USER = "INSERT INTO SHOP_ADMIN.Felhasznalo VALUES (?, ?, ?, ?, ?, ?, ?)";

    // Lakcim
    public static String SELECT_ALL_ADDRESSES = "SELECT id, varos, utca, hazszam FROM shop_admin.Lakcim";
    public static String SELECT_ADDRESSES_BY_ID = "SELECT id, varos, utca, hazszam FROM shop_admin.Lakcim WHERE id = ?";

    // Kategoria
    public static String SELECT_CATEGORIES = "SELECT id, nev FROM SHOP_ADMIN.kategoria";

    //Termek
    public static String SELECT_PRODUCTS_BY_PRODUCTTYPE = "SELECT id, uzletId, termekFajtaId, kategoriaId, nev, ar, leiras " +
            "FROM SHOP_ADMIN.Termek WHERE termekFajtaId = ?";


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

}
