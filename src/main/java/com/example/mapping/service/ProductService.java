package com.example.mapping.service;

import com.example.mapping.entity.Product;
import com.example.mapping.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository prodRepo;

    @Transactional
    public List<Product> getProductByUserName(String name){
        log.info("ID: {}", name);
        List<Product> products = prodRepo.findByUser_Name(name);
        log.info("Products: {}", products.size());
        return products;
    }
}