package com.example.kisvakondkerteszbolt.repository;

import com.example.kisvakondkerteszbolt.SqlQueries;
import com.example.kisvakondkerteszbolt.model.*;
import com.example.kisvakondkerteszbolt.repository.rowmapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlTypeValue;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
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

    public List<Rendeles> selectOrdersOfUser(int userId) {
        List<Rendeles> result = jdbcTemplate.query(
                SqlQueries.SELECT_ORDERS_OF_USER,
                new Object[]{userId},
                new RendelesRowMapper()
        );
        return result;
    }

    public List<RendelesTermek> selectOrderedProductsOfOrder(int orderId) {
        List<RendelesTermek> result = jdbcTemplate.query(
                SqlQueries.SELECT_RENDELES_TERMEK,
                new Object[]{orderId},
                new RendelesTermekRowMapper()
        );
        return result;
    }

    public Termek selectProduct(int productId) {
        try {
            return (Termek) jdbcTemplate.queryForObject(
                    SqlQueries.SELECT_PRODUCT,
                    new Object[]{productId},
                    new TermekRowMapper()
            );
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<Szolgaltatas> selectServicesOfOrder(int orderId) {
        List<Szolgaltatas> result = jdbcTemplate.query(
                SqlQueries.SELECT_SERVICES_BY_ORDER_ID,
                new Object[]{orderId},
                new SzolgaltatasRowMapper()
        );
        return result;
    }

    public List<OrderInfo> selectOrderInfoByUser(int userId) {
        List<OrderInfo> result = new ArrayList<>();
        List<Rendeles> orders = selectOrdersOfUser(userId);
        for (Rendeles order : orders) {
            List<RendelesTermek> orderedProducts = selectOrderedProductsOfOrder(order.rendelesSzam);
            List<TermekMennyiseggel> termekMennyisegek = new ArrayList<>();
            for (RendelesTermek orderedProduct : orderedProducts) {
                Termek product = selectProduct(orderedProduct.termekId);
                termekMennyisegek.add(new TermekMennyiseggel(product.id, product.uzletId, product.termekFajtaId,
                        product.kategoriaId, product.nev, product.ar, product.leiras, orderedProduct.mennyiseg));
            }
            List<Szolgaltatas> services = selectServicesOfOrder(order.rendelesSzam);
            OrderInfo orderInfo = new OrderInfo(order.rendelesSzam, order.mikor, termekMennyisegek, services);
            result.add(orderInfo);
        }
        return result;
    }

    public void rate(Ertekeles rating) {
        jdbcTemplate.update(
                SqlQueries.INSERT_RATING,
                rating.felhasznaloId,
                rating.termekId != 0 ? rating.termekId : null,
                rating.szolgaltatasId != 0 ? rating.szolgaltatasId : null,
                rating.szoveg,
                rating.csillag
        );
    }
}
