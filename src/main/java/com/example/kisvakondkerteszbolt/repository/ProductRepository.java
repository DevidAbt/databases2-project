package com.example.kisvakondkerteszbolt.repository;

import com.example.kisvakondkerteszbolt.SqlQueries;
import com.example.kisvakondkerteszbolt.model.Kategoria;
import com.example.kisvakondkerteszbolt.model.Termekfajta;
import com.example.kisvakondkerteszbolt.repository.rowmapper.KategoriaRowMapper;
import com.example.kisvakondkerteszbolt.repository.rowmapper.LakcimRowMapper;
import com.example.kisvakondkerteszbolt.repository.rowmapper.TermekfajtaRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Kategoria> selectCategories() {
        List<Kategoria> result = jdbcTemplate.query(
                SqlQueries.SELECT_CATEGORIES,
                new KategoriaRowMapper()
        );
        return result;
    }

    public List<Termekfajta> selectProductTypesByCategory(int categoryId) {
        List<Termekfajta> result = jdbcTemplate.query(
                SqlQueries.SELECT_PRODUCTTYPE_BY_CATEGORY,
                new Object[]{categoryId},
                new TermekfajtaRowMapper()
        );
        return result;
    }
}
