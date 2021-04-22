package com.example.kisvakondkerteszbolt.repository.rowmapper;

import com.example.kisvakondkerteszbolt.model.Felhasznalo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FelhasznaloRowMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        Felhasznalo felhasznalo = new Felhasznalo(
                        resultSet.getInt("id"),
                        resultSet.getString("felhasznalonev"),
                        resultSet.getString("nev"),
                        resultSet.getString("hash"),
                        resultSet.getString("telefonszam"),
                        resultSet.getString("email"),
                        resultSet.getInt("lakcimId")
        );
        return felhasznalo;
    }
}
