package com.example.kisvakondkerteszbolt.repository.rowmapper;

import com.example.kisvakondkerteszbolt.model.Ertekeles;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ErtekelesRowMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        Ertekeles ertekeles = new Ertekeles(
                resultSet.getInt("id"),
                resultSet.getInt("felhasznaloId"),
                resultSet.getInt("termekId"),
                resultSet.getInt("szolgaltatasId"),
                resultSet.getString("datum"),
                resultSet.getString("szoveg"),
                resultSet.getInt("csillag")
        );
        return ertekeles;
    }
}
