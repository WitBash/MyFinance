package com.boshko.MyFinance.controllers;

import com.boshko.MyFinance.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
public class ProductsController {
    private ProductServiceImpl productServiceImpl;

    @Autowired
    public ProductsController(ProductServiceImpl productServiceImpl) {
        this.productServiceImpl = productServiceImpl;
    }

    public ProductsController() {
    }

    @GetMapping("/")
    public String showAllProducts(Model model) {
        model.addAttribute("products",productServiceImpl.findAll());
        return "products";
    }

    @GetMapping("{id}")
    public String showProductById(Model model,@PathVariable("id") Long id) {
        model.addAttribute("products",productServiceImpl.findById(id));
        return "products";
    }


}
