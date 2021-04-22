package com.example.kisvakondkerteszbolt;

@SuppressWarnings("SpellCheckingInspection")
public class SqlQueries {
    // Felhasznalo
    public static String SELECT_USER = "SELECT id, felhasznalonev, nev, hash, telefonszam, email, lakcimId FROM shop_admin.Felhasznalo WHERE id = ?";
    public static String SELECT_USER_BY_USERNAME = "SELECT id, felhasznalonev, nev, hash, telefonszam, email, lakcimId FROM shop_admin.Felhasznalo WHERE felhasznalonev = ?";
    public static String INSERT_USER = "INSERT INTO SHOP_ADMIN.Felhasznalo VALUES (?, ?, ?, ?, ?, ?, ?)";

    // Lakcim
    public static String SELECT_ALL_ADDRESSES = "SELECT id, varos, utca, hazszam FROM shop_admin.Lakcim";
}
