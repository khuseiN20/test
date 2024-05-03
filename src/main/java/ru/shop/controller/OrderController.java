package ru.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.shop.model.Customer;
import ru.shop.model.Order;
import ru.shop.model.Product;
import ru.shop.repository.OrderRepository;
import ru.shop.service.CustomerService;
import ru.shop.service.OrderService;
import ru.shop.service.ProductService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final CustomerService customerService;
    private final ProductService productService;

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.findAll();
    }

    @GetMapping("/{id}")
    public Order getById(@PathVariable UUID id) {
        return orderService.getById(id);
    }

    @GetMapping("/customer/total")
    public long getCutomerTotal(UUID customerId){
        Customer customer = customerService.getById(customerId);
        return orderService.getTotalCustomerAmount(customer);
    }

    @PostMapping
    public void save(UUID productId, UUID customerId, int count){
        Customer customer = customerService.getById(customerId);
        Product product = productService.getById(productId);
        orderService.add(customer,product,count);
    }
}