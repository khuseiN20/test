package ru.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.shop.model.Order;
import ru.shop.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> { }
//public class ProductRepository implements IRepository<Product> {
//
//    List<Product> products = new ArrayList<>();
//
//    @Override
//    public void save(Product product) {
//        products.add(product);
//    }
//
//    @Override
//    public List<Product> findAll() {
//        return products;
//    }
//
//    @Override
//    public Optional<Product> findById(UUID id) {
//        return findAll().stream().
//                filter(product -> product.getId().equals(id))
//                .findFirst();
//    }
//}
