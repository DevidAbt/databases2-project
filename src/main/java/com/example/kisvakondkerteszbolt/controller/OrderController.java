package com.example.kisvakondkerteszbolt.controller;

import com.example.kisvakondkerteszbolt.SqlQueries;
import com.example.kisvakondkerteszbolt.model.Rendeles;
import com.example.kisvakondkerteszbolt.model.RendelesKiszallitas;
import com.example.kisvakondkerteszbolt.model.Termek;
import com.example.kisvakondkerteszbolt.model.TermekMennyiseg;
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

    @RequestMapping(value = "/",
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
    void orderProducts(@RequestBody List<TermekMennyiseg> termekMennyiseg) {
        orderRepository.orderProducts(termekMennyiseg);
    }

    @RequestMapping(value = "/services",
            method = RequestMethod.POST,
            produces = "application/json",
            consumes = {"application/json"})
    public @ResponseBody
    void orderServices(@RequestBody List<Integer> serviceIds) {
        orderRepository.orderServices(serviceIds);
    }
}
