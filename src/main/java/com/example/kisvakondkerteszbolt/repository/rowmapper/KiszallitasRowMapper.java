package com.example.kisvakondkerteszbolt.repository.rowmapper;

import com.example.kisvakondkerteszbolt.model.Kiszallitas;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class KiszallitasRowMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        Kiszallitas kiszallitas = new Kiszallitas(
                resultSet.getInt("kiszallitasiSzam"),
                resultSet.getInt("felhasznaloId"),
                resultSet.getString("atvevoNev"),
                resultSet.getInt("lakcimId")
        );
        return kiszallitas;
    }
}
