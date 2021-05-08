package com.example.kisvakondkerteszbolt.repository.rowmapper;

import com.example.kisvakondkerteszbolt.model.Adminisztrator;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminisztratorRowMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        Adminisztrator adminisztrator = new Adminisztrator(
                resultSet.getInt("id"),
                resultSet.getInt("termeketHozzaadhat"),
                resultSet.getInt("moderalhat"),
                resultSet.getString("beosztas"),
                resultSet.getInt("szolgaltatastHozzaadhat")
        );
        return adminisztrator;
    }
}
