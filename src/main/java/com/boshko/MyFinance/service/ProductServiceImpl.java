package com.boshko.MyFinance.service;

import com.boshko.MyFinance.entities.Product;
import com.boshko.MyFinance.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    @Cacheable("products")
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    @Cacheable("products")
    public Product findById(Long id) {
        return productRepository.findById(id).get();
    }

    @Override
    @CacheEvict("products")
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    @CachePut(value = "products", key = "#product.id")
    public void save(Product product) throws IOException {
        productRepository.save(product);
    }
}
