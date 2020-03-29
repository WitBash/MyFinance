package com.boshko.MyFinance.service;

import com.boshko.MyFinance.entities.Product;
import com.boshko.MyFinance.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    private Map<Long, Product> identityMap = new HashMap<>();

    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        products = productRepository.findAll();
        for (Product o : products
        ) {
            identityMap.put(o.getId(), o);
        }

        return products;
    }

    @Override
    public Product findById(Long id) {
        if (identityMap.containsKey(id)) {
            return identityMap.get(id);
        }
        Product product = productRepository.findById(id).get();
        identityMap.put(product.getId(), product);
        return product;
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
        identityMap.remove(id);
    }

    @Override
    public void save(Product product) throws IOException {
        identityMap.put(product.getId(), product);
        productRepository.save(product);
    }
}
