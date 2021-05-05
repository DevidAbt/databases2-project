package com.example.kisvakondkerteszbolt.controller;

import com.example.kisvakondkerteszbolt.SqlQueries;
import com.example.kisvakondkerteszbolt.model.*;
import com.example.kisvakondkerteszbolt.repository.OrderRepository;
import com.example.kisvakondkerteszbolt.repository.rowmapper.LakcimRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/order", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderController {
    @Autowired
    OrderRepository orderRepository;

    @RequestMapping(value = "/place",
            method = RequestMethod.POST,
            produces = "application/json",
            consumes = {"application/json"})
    public @ResponseBody
    boolean order(@RequestBody RendelesKiszallitas rendelesKiszallitas) {
        return orderRepository.order(rendelesKiszallitas);
    }

    @RequestMapping(value = "/products",
            method = RequestMethod.POST,
            produces = "application/json",
            consumes = {"application/json"})
    public @ResponseBody
    boolean orderProducts(@RequestBody List<TermekMennyiseg> termekMennyiseg) {
        orderRepository.orderProducts(termekMennyiseg);
        return true;
    }

    @RequestMapping(value = "/services",
            method = RequestMethod.POST,
            produces = "application/json",
            consumes = {"application/json"})
    public @ResponseBody
    boolean orderServices(@RequestBody List<Integer> serviceIds) {
        orderRepository.orderServices(serviceIds);
        return true;
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public @ResponseBody
    List<OrderInfo> getOrderInfoByUser(@RequestParam(value = "felhasznaloId") int categoryId) {
        return orderRepository.selectOrderInfoByUser(categoryId);
    }

    @RequestMapping(value = "/rate",
            method = RequestMethod.POST,
            produces = "application/json",
            consumes = {"application/json"})
    public @ResponseBody
    boolean rate(@RequestBody Ertekeles rating) {
        orderRepository.rate(rating);
        return true;
    }
}
