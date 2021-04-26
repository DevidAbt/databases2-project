package com.example.kisvakondkerteszbolt.controller;

import com.example.kisvakondkerteszbolt.model.Kategoria;
import com.example.kisvakondkerteszbolt.model.Termek;
import com.example.kisvakondkerteszbolt.model.Termekfajta;
import com.example.kisvakondkerteszbolt.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/product", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController {
    @Autowired
    ProductRepository productRepository;

    @RequestMapping(value = "/categories", method = RequestMethod.GET)
    public @ResponseBody
    List<Kategoria> getCategories() {
        return productRepository.selectCategories();
    }

    @RequestMapping(value = "/types", method = RequestMethod.GET)
    public @ResponseBody
    List<Termekfajta> getProductTypes(@RequestParam(value = "kategoriaId") int categoryId) {
        return productRepository.selectProductTypesByCategory(categoryId);
    }

    @RequestMapping(value = "/type", method = RequestMethod.GET)
    public @ResponseBody
    List<Termek> getProductsOfProductType(@RequestParam(value = "termekFajtaId") int productTypeId) {
        return productRepository.selectProductsByProductType(productTypeId);
    }
}
