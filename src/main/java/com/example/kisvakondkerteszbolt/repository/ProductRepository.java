package com.example.kisvakondkerteszbolt.repository;

import com.example.kisvakondkerteszbolt.SqlQueries;
import com.example.kisvakondkerteszbolt.model.*;
import com.example.kisvakondkerteszbolt.repository.rowmapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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

    public List<Termek> selectProductsByProductType(int productTypeId) {
        List<Termek> result = jdbcTemplate.query(
                SqlQueries.SELECT_PRODUCTS_BY_PRODUCTTYPE,
                new Object[]{productTypeId},
                new TermekRowMapper()
        );
        return result;
    }

    public List<TermekInfo> selectProductsInfo(int productTypeId) {
        List<TermekInfo> result = jdbcTemplate.query(
                SqlQueries.SELECT_PRODUCTS_INFO,
                new Object[]{productTypeId},
                new TermekInfoRowMapper()
        );
        return result;
    }

    public Lakcim selectShopByProduct(int productId) {
        try {
            return  (Lakcim) jdbcTemplate.queryForObject(
                    SqlQueries.SELECT_SHOP_BY_PRODUCT,
                    new Object[]{productId},
                    new LakcimRowMapper()
            );
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public UzletInfo selectShopInfoByProduct(int productId) {
        try {
            return  (UzletInfo) jdbcTemplate.queryForObject(
                    SqlQueries.SELECT_SHOP_INFO_BY_PRODUCT,
                    new Object[]{productId},
                    new UzletInfoRowMapper()
            );
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<TermekInfo> selectProductsInfoByName(String name) {
        List<TermekInfo> result = jdbcTemplate.query(
                SqlQueries.SELECT_PRODUCTS_INFO_BY_NAME,
                new Object[]{"%" + name + "%"},
                new TermekInfoRowMapper()
        );
        return result;
    }
}
