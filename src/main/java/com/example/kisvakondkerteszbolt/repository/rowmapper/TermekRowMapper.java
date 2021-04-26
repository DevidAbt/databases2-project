package com.example.kisvakondkerteszbolt.repository.rowmapper;

import com.example.kisvakondkerteszbolt.model.Termek;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TermekRowMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        Termek termek = new Termek(
                resultSet.getInt("id"),
                resultSet.getInt("uzletId"),
                resultSet.getInt("termekFajtaId"),
                resultSet.getInt("kategoriaId"),
                resultSet.getString("nev"),
                resultSet.getInt("ar"),
                resultSet.getString("leiras")
        );
        return termek;
    }
}
