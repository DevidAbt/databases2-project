package com.example.kisvakondkerteszbolt.repository.rowmapper;

import com.example.kisvakondkerteszbolt.model.RendelesInfo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RendelesInfoRowMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        RendelesInfo rendelesInfo = new RendelesInfo(
                resultSet.getInt("rendelesszam"),
                resultSet.getInt("felhasznaloid"),
                resultSet.getString("mikor"),
                resultSet.getInt("kuponKod"),
                resultSet.getString("felhasznalonev"),
                resultSet.getInt("termekid"),
                resultSet.getString("termeknev"),
                resultSet.getInt("mennyiseg"),
                resultSet.getString("nev")
        );
        return rendelesInfo;
    }
}
