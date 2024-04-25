package ru.shop.repository;

import org.springframework.stereotype.Repository;
import ru.shop.model.Product;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepository implements IRepository<Product> {

    List<Product> products = new ArrayList<>();

    public void save(Product product) {
        products.add(product);
    }

    public List<Product> findAll() {
        return products;
    }

}
