package com.example.kisvakondkerteszbolt.controller;

import com.example.kisvakondkerteszbolt.model.*;
import com.example.kisvakondkerteszbolt.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/service", produces = MediaType.APPLICATION_JSON_VALUE)
public class ServiceController {
    @Autowired
    ServiceRepository serviceRepository;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public @ResponseBody
    List<Szolgaltatas> getServices() {
        return serviceRepository.selectServices();
    }
}
