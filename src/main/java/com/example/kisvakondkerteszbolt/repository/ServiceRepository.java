package com.example.kisvakondkerteszbolt.repository;

import com.example.kisvakondkerteszbolt.SqlQueries;
import com.example.kisvakondkerteszbolt.model.*;
import com.example.kisvakondkerteszbolt.repository.rowmapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ServiceRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Szolgaltatas> selectServices() {
        List<Szolgaltatas> result = jdbcTemplate.query(
                SqlQueries.SELECT_ALL_SERVICES,
                new SzolgaltatasRowMapper()
        );
        return result;
    }

    public List<Ertekeles> selectRatings(int serviceId) {
        List<Ertekeles> result = jdbcTemplate.query(
                SqlQueries.SELECT_RATINGS,
                new Object[]{serviceId, serviceId},
                new ErtekelesRowMapper()
        );
        return result;
    }
}
