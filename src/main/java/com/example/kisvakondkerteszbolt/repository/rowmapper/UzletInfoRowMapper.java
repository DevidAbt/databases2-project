package com.example.kisvakondkerteszbolt.repository.rowmapper;

import com.example.kisvakondkerteszbolt.model.UzletInfo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

public class UzletInfoRowMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        Time nyitas = resultSet.getTime("nyitas");
        Time zaras = resultSet.getTime("zaras");
        UzletInfo uzletInfo = new UzletInfo(
                resultSet.getInt("id"),
                resultSet.getString("varos"),
                resultSet.getString("utca"),
                resultSet.getString("hazszam"),
                nyitas.toString().substring(0, 5),
                zaras.toString().substring(0, 5)
        );
        return uzletInfo;
    }
}
