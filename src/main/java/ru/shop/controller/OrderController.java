package ru.shop.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.shop.model.Order;
import ru.shop.model.Product;
import ru.shop.repository.OrderRepository;
import ru.shop.service.OrderService;

import java.util.List;


@RestController
@RequestMapping("/order")
public class OrderController {
    OrderService orderService = new OrderService(new OrderRepository());

    @GetMapping
    public List<Order> getAllProducts() {
        return orderService.findAll();
    }

    //5.3
}

