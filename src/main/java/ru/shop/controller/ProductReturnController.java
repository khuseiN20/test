package ru.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.shop.model.Order;
import ru.shop.model.ProductReturn;
import ru.shop.service.ProductReturnService;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product-returns")
public class ProductReturnController {
    private ProductReturnService productReturnService;

    @GetMapping
    public List<ProductReturn> getAll() {
        return productReturnService.findAll();
    }

    @GetMapping("/{id}")
    public ProductReturn getById(@PathVariable UUID id) {
        return productReturnService.findById(id);
    }

    //Для добавления, по сути для проверки
    @PostMapping()
    public void addProductReturn(@RequestBody Order order, @RequestBody long count) {
        productReturnService.add(order, count);
    }
}
