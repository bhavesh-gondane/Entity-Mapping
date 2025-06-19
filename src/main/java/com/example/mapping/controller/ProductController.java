package com.example.mapping.controller;

import com.example.mapping.entity.Product;
import com.example.mapping.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping
    public ResponseEntity<?> getAllProducts(@RequestParam(defaultValue = "1")int pageNo,@RequestParam(defaultValue = "10")int pageSize){
        return ResponseEntity.ok(productService.getAllProducts(pageNo,pageSize));
    }
}
