package com.boshko.MyFinance.service;

import com.boshko.MyFinance.entities.Product;

import java.io.IOException;
import java.util.List;

public interface ProductService {
    List<Product> findAll();

    Product findById(Long id);

    void deleteById(Long id);

    void save(Product product) throws IOException;
}
