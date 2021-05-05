package com.example.kisvakondkerteszbolt.repository.rowmapper;

import com.example.kisvakondkerteszbolt.model.RendelesSzolgaltatas;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RendelesSzolgaltatasRowMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        RendelesSzolgaltatas rendelesSzolgaltatas = new RendelesSzolgaltatas(
                resultSet.getInt("rendelesSzam"),
                resultSet.getInt("szolgaltatasId")
        );
        return rendelesSzolgaltatas;
    }
}
