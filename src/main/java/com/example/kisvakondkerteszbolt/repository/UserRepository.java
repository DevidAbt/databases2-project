package com.example.kisvakondkerteszbolt.repository;

import com.example.kisvakondkerteszbolt.SqlQueries;
import com.example.kisvakondkerteszbolt.model.Felhasznalo;
import com.example.kisvakondkerteszbolt.model.FelhasznaloInfo;
import com.example.kisvakondkerteszbolt.model.Lakcim;
import com.example.kisvakondkerteszbolt.repository.rowmapper.FelhasznaloRowMapper;
import com.example.kisvakondkerteszbolt.repository.rowmapper.LakcimRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
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
                new LakcimRowMapper()
        );
        return result;
    }

    public Lakcim selectAddressById(int id) {
        try {
            return (Lakcim) jdbcTemplate.queryForObject(
                    SqlQueries.SELECT_ADDRESSES_BY_ID,
                    new Object[]{id},
                    new LakcimRowMapper()
            );
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public Felhasznalo selectUser(int id) {
        try {
            return (Felhasznalo) jdbcTemplate.queryForObject(
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
            return (Felhasznalo) jdbcTemplate.queryForObject(
                    SqlQueries.SELECT_USER_BY_USERNAME,
                    new Object[]{username},
                    new FelhasznaloRowMapper()
            );
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public boolean register(FelhasznaloInfo userInfo) {
        Felhasznalo user = selectUserByUsername(userInfo.felhasznalonev);
        if (user != null) {
            return false; // user exists
        }

        String passwordHash = passwordEncoder.encode(userInfo.hash);

        try {
            Connection connection = jdbcTemplate.getDataSource().getConnection();
            CallableStatement callableStatement = connection.prepareCall(SqlQueries.REGISTER);
            callableStatement.setString(1, userInfo.felhasznalonev);
            callableStatement.setString(2, userInfo.nev);
            callableStatement.setString(3, passwordHash);
            callableStatement.setString(4, userInfo.telefonszam);
            callableStatement.setString(5, userInfo.email);
            callableStatement.setString(6, userInfo.varos);
            callableStatement.setString(7, userInfo.utca);
            callableStatement.setString(8, userInfo.hazszam);
            callableStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }

        return true;
    }

    public Felhasznalo login(String username, String password) {
        Felhasznalo user = selectUserByUsername(username);
        if (user == null) {
            return null; // user not exists
        }

        if (!passwordEncoder.matches(password, user.hash)) {
            return null;
        }

        user.hash = null;

        return user;
    }
}
