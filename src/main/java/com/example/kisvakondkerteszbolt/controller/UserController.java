package com.example.kisvakondkerteszbolt.controller;

import com.example.kisvakondkerteszbolt.model.Felhasznalo;
import com.example.kisvakondkerteszbolt.model.Lakcim;
import com.example.kisvakondkerteszbolt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public @ResponseBody
    Felhasznalo getUser(@RequestParam(value = "id") int id) {
        return userRepository.selectUser(id);
    }

    @RequestMapping(value = "/register",
            method = RequestMethod.POST,
            produces = "application/json",
            consumes = {"application/json"})
    public @ResponseBody
    boolean register(@RequestBody Felhasznalo user) {
        return userRepository.register(user);
    }


    @GetMapping("/address/all")
    public List<Lakcim> getAddresses() {
        return userRepository.selectAllAddresses();
    }

}
