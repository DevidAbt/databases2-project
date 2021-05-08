package com.example.kisvakondkerteszbolt.controller;

import com.example.kisvakondkerteszbolt.model.Felhasznalo;
import com.example.kisvakondkerteszbolt.model.FelhasznaloAdmin;
import com.example.kisvakondkerteszbolt.model.FelhasznaloInfo;
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
    boolean register(@RequestBody FelhasznaloInfo userInfo) {
        return userRepository.register(userInfo);
    }

    @RequestMapping(value = "/login",
            method = RequestMethod.POST,
            produces = "application/json",
            consumes = {"application/json"})
    public @ResponseBody
    FelhasznaloAdmin login(@RequestBody Felhasznalo user) {
        return userRepository.login(user.felhasznalonev, user.hash);
    }

    @GetMapping("/address/all")
    public List<Lakcim> getAddresses() {
        return userRepository.selectAllAddresses();
    }

    @RequestMapping(value = "/address", method = RequestMethod.GET)
    public @ResponseBody
    Lakcim getAddress(@RequestParam(value = "id") int id) {
        return userRepository.selectAddressById(id);
    }

}
