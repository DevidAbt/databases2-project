package com.example.kisvakondkerteszbolt.repository.rowmapper;

import com.example.kisvakondkerteszbolt.model.Termek;
import com.example.kisvakondkerteszbolt.model.TermekInfo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TermekInfoRowMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        TermekInfo termekInfo = new TermekInfo(
                resultSet.getInt("id"),
                resultSet.getInt("uzletId"),
                resultSet.getString("termekFajta"),
                resultSet.getString("kategoria"),
                resultSet.getString("nev"),
                resultSet.getInt("ar"),
                resultSet.getString("leiras")
        );
        return termekInfo;
    }
}
