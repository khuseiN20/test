package ru.shop.service;

import lombok.RequiredArgsConstructor;
import ru.shop.model.Customer;
import ru.shop.repository.IRepository;

import java.util.List;

@RequiredArgsConstructor
public class CustomerService {

    private final IRepository<Customer> repository;

    public void save(Customer customer) {
        repository.save(customer);
    }

    public List<Customer> findAll() {
        return repository.findAll();
    }

}
