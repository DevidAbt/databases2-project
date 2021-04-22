package com.example.kisvakondkerteszbolt.repository.rowmapper;

import com.example.kisvakondkerteszbolt.model.Termekfajta;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TermekfajtaRowMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        Termekfajta termekfajta = new Termekfajta(
                resultSet.getInt("id"),
                resultSet.getString("nev")
        );
        return termekfajta;
    }
}
