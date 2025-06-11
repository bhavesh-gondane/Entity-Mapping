package com.example.mapping.service;

import com.example.mapping.entity.Product;
import com.example.mapping.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository prodRepo;

    public List<Product> getProductByUserName(String name){
        log.info("ID: {}", name);
        List<Product> products = prodRepo.productsByName(name);
        log.info("Products: {}", products.size());
        return products;
    }
}
