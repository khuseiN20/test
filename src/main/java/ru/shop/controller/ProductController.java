package ru.shop.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.shop.model.Customer;
import ru.shop.model.Product;
import ru.shop.repository.ProductRepository;
import ru.shop.service.ProductService;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/product")
public class ProductController {

    ProductService productService = new ProductService(new ProductRepository());

    @GetMapping
    public List<Product> getAllProducts() {
        //return  list;
        return productService.findAll();
    }

    //5.3
}

