package ru.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.shop.exception.BadOrderCountException;
import ru.shop.exception.EntityNotFoundException;
import ru.shop.model.Customer;
import ru.shop.model.Order;
import ru.shop.model.Product;
import ru.shop.repository.IRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final IRepository<Order> repository;
    private final CustomerService customerService;
    private final ProductService productService;

    public void add(UUID productId, UUID customerId, int count) {
        if (count <= 0) {
            throw new BadOrderCountException();
        }
        Customer customer = customerService.getById(customerId);
        Product product = productService.getById(productId);
        Order order = new Order(UUID.randomUUID(), customer.getId(), product.getId(), count, product.getCost() * count);
        repository.save(order);
    }

    public List<Order> findByCustomer(UUID customerId) {
        Customer customer = customerService.getById(customerId);
        List<Order> result = new ArrayList<>();
        for (Order order : repository.findAll()) {
            if (order.getCustomerId() == customer.getId()) {
                result.add(order);
            }
        }
        return result;
    }

    public long getTotalCustomerAmount(UUID customerId) {
        List<Order> customerOrders = findByCustomer(customerId);
        long result = 0;
        for (Order order : customerOrders) {
            result += order.getAmount();
        }
        return result;
    }

    public List<Order> findAll() {
        return repository.findAll();
    }

    public Order getById(UUID id) {
        return repository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}