package com.example.kisvakondkerteszbolt.controller;

import com.example.kisvakondkerteszbolt.model.*;
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

    @RequestMapping(value = "/type/info", method = RequestMethod.GET)
    public @ResponseBody
    List<TermekInfo> getProductsInfo(@RequestParam(value = "termekFajtaId") int productTypeId) {
        return productRepository.selectProductsInfo(productTypeId);
    }

    @RequestMapping(value = "/shop", method = RequestMethod.GET)
    public @ResponseBody
    Lakcim getShopByProduct(@RequestParam(value = "termekId") int productId) {
        return productRepository.selectShopByProduct(productId);
    }

    @RequestMapping(value = "/shop/info", method = RequestMethod.GET)
    public @ResponseBody
    UzletInfo getShopInfoByProduct(@RequestParam(value = "termekId") int productId) {
        return productRepository.selectShopInfoByProduct(productId);
    }

    @RequestMapping(value = "/search/name", method = RequestMethod.GET)
    public @ResponseBody
    List<TermekInfo> getProductsInfoByName(@RequestParam(value = "nev") String name) {
        return productRepository.selectProductsInfoByName(name);
    }

    @RequestMapping(value = "/ratings", method = RequestMethod.GET)
    public @ResponseBody
    List<Ertekeles> getRatings(@RequestParam(value = "termekId") int productId) {
        return productRepository.selectRatings(productId);
    }

    @RequestMapping(value = "/add",
            method = RequestMethod.POST,
            produces = "application/json",
            consumes = {"application/json"})
    public @ResponseBody
    boolean getRatings(@RequestBody Termek  product) {
        return productRepository.insertProduct(product);
    }
}
