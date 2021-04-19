package com.example.kisvakondkerteszbolt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Lakcim> findAllAddresses() {
        List<Lakcim> result = jdbcTemplate.query(
                "SELECT id, varos, utca, hazszam FROM Lakcim",
                (rs, rowNum) -> new Lakcim(rs.getInt("id"),
                                           rs.getString("varos"),
                                           rs.getString("utca"),
                                           rs.getString("hazszam"))
        );
        return result;
    }
}
