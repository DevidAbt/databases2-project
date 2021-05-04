package com.example.kisvakondkerteszbolt.repository;

import com.example.kisvakondkerteszbolt.SqlQueries;
import com.example.kisvakondkerteszbolt.model.Rendeles;
import com.example.kisvakondkerteszbolt.model.RendelesKiszallitas;
import com.example.kisvakondkerteszbolt.model.TermekMennyiseg;
import com.example.kisvakondkerteszbolt.repository.rowmapper.LakcimRowMapper;
import com.example.kisvakondkerteszbolt.repository.rowmapper.RendelesRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlTypeValue;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

@Repository
public class OrderRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean order(RendelesKiszallitas rendelesKiszallitas) {
        try {
            Connection connection = jdbcTemplate.getDataSource().getConnection();
            CallableStatement callableStatement = connection.prepareCall(SqlQueries.RENDELES_KISZALLITAS);
            callableStatement.setInt(1, rendelesKiszallitas.felhasznaloId);
            if (rendelesKiszallitas.kuponKod == 0) {
                callableStatement.setNull(2, Types.INTEGER);
            } else {
                callableStatement.setInt(2, rendelesKiszallitas.kuponKod);
            }
            callableStatement.setString(3, rendelesKiszallitas.atvevoNev);
            callableStatement.setInt(4, rendelesKiszallitas.lakcimId);
            callableStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }

        return true;
    }

    public Rendeles selectLastOrder() {
        try {
            return (Rendeles) jdbcTemplate.queryForObject(
                    SqlQueries.SELECT_LAST_ORDER,
                    new RendelesRowMapper()
            );
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public void orderProducts(List<TermekMennyiseg> productIds) {
        Rendeles lastOrder = selectLastOrder();

        for (TermekMennyiseg termekMennyiseg : productIds) {
            jdbcTemplate.update(
                    SqlQueries.INSERT_RENDELES_TERMEK,
                    lastOrder.rendelesSzam,
                    termekMennyiseg.id,
                    termekMennyiseg.mennyiseg
            );
        }
    }

    public void orderServices(List<Integer> serviceIds) {
        Rendeles lastOrder = selectLastOrder();

        for (int id : serviceIds) {
            jdbcTemplate.update(
                    SqlQueries.INSERT_RENDELES_SZOLGALTATAS,
                    lastOrder.rendelesSzam,
                    id
            );
        }
    }
}
