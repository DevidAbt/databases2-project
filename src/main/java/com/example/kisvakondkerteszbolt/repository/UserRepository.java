package com.example.kisvakondkerteszbolt.repository;

import com.example.kisvakondkerteszbolt.SqlQueries;
import com.example.kisvakondkerteszbolt.model.Felhasznalo;
import com.example.kisvakondkerteszbolt.model.Lakcim;
import com.example.kisvakondkerteszbolt.repository.rowmapper.FelhasznaloRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private BCryptPasswordEncoder passwordEncoder;

    public UserRepository() {
        passwordEncoder = new BCryptPasswordEncoder();
    }

    public List<Lakcim> selectAllAddresses() {
        List<Lakcim> result = jdbcTemplate.query(
                SqlQueries.SELECT_ALL_ADDRESSES,
                (rs, rowNum) -> new Lakcim(
                        rs.getInt("id"),
                        rs.getString("varos"),
                        rs.getString("utca"),
                        rs.getString("hazszam")
                )
        );
        return result;
    }

    public Felhasznalo selectUser(int id) {
        try {
            return  (Felhasznalo) jdbcTemplate.queryForObject(
                    SqlQueries.SELECT_USER,
                    new Object[]{id},
                    new FelhasznaloRowMapper()
            );
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    private Felhasznalo selectUserByUsername(String username) {
        try {
            return  (Felhasznalo) jdbcTemplate.queryForObject(
                    SqlQueries.SELECT_USER_BY_USERNAME,
                    new Object[]{username},
                    new FelhasznaloRowMapper()
            );
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    public boolean register(Felhasznalo newUser) {
        Felhasznalo user = selectUserByUsername(newUser.felhasznalonev);
        if (user != null) {
            return  false; // user exists
        }

        String passwordHash = passwordEncoder.encode(newUser.hash);

        jdbcTemplate.update(
                SqlQueries.INSERT_USER,
                newUser.id,
                newUser.felhasznalonev,
                newUser.nev,
                passwordHash,
                newUser.telefonszam,
                newUser.email,
                newUser.lakcimId
        );

        return true;
    }
}
