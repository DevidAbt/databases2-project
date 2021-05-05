package com.example.kisvakondkerteszbolt.repository.rowmapper;

import com.example.kisvakondkerteszbolt.model.RendelesTermek;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RendelesTermekRowMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        RendelesTermek rendelesTermek = new RendelesTermek(
                resultSet.getInt("rendelesSzam"),
                resultSet.getInt("termekId"),
                resultSet.getInt("mennyiseg")
        );
        return rendelesTermek;
    }
}
