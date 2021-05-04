package com.example.kisvakondkerteszbolt.repository.rowmapper;

import com.example.kisvakondkerteszbolt.model.Rendeles;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RendelesRowMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        Rendeles rendeles = new Rendeles(
                resultSet.getInt("rendelesSzam"),
                resultSet.getInt("felhasznaloId"),
                resultSet.getString("mikor"),
                resultSet.getInt("kuponKod")
        );
        return rendeles;
    }
}
