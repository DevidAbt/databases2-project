package com.example.kisvakondkerteszbolt.repository;

import com.example.kisvakondkerteszbolt.SqlQueries;
import com.example.kisvakondkerteszbolt.model.Lakcim;
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
                SqlQueries.SELECT_ALL_ADDRESSES,
                (rs, rowNum) -> new Lakcim(rs.getInt("id"),
                                           rs.getString("varos"),
                                           rs.getString("utca"),
                                           rs.getString("hazszam"))
        );
        return result;
    }
}
