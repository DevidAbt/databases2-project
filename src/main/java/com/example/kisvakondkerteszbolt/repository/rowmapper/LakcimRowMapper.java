package com.example.kisvakondkerteszbolt.repository.rowmapper;

import com.example.kisvakondkerteszbolt.model.Lakcim;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LakcimRowMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        Lakcim lakcim = new Lakcim(
                resultSet.getInt("id"),
                resultSet.getString("varos"),
                resultSet.getString("utca"),
                resultSet.getString("hazszam")
        );
        return lakcim;
    }
}
