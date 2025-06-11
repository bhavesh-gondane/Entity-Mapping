package com.example.mapping.controller;

import com.example.mapping.entity.Product;
import com.example.mapping.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    @GetMapping("/user/{name}")
    public List<Product> getProductsByName(@PathVariable String name){
        return productService.getProductByUserName(name);
    }
}
