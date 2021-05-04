package com.example.kisvakondkerteszbolt.repository.rowmapper;

import com.example.kisvakondkerteszbolt.model.Szolgaltatas;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SzolgaltatasRowMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        Szolgaltatas szolgaltatas = new Szolgaltatas(
                resultSet.getInt("id"),
                resultSet.getString("nev"),
                resultSet.getInt("ar"),
                resultSet.getString("leiras")
        );
        return szolgaltatas;
    }
}
